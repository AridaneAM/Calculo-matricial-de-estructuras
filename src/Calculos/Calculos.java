package Calculos;
import Jama.*;

public class Calculos {

    Matrix nodos_x;
    static Matrix nodos_u;
    int n_nodos;
    int n_elementos;
    static Matrix elementos_A;
    Matrix elementos_E;
    Matrix elementos_nodos;
    static Matrix elementos_N;
    int n_cargas;
    Matrix cargas_nodo;
    Matrix cargas_F;
    int n_apoyos;
    Matrix apoyos_nodo;
    Matrix apoyos_tipo;
    Matrix MK;
    Matrix MF;
    Matrix sol;
    static Matrix apoyos_R;
    static Matrix NODOS1, BARRAS1, APOYOS1,CARGAS1;



    public Matrix calcular_Kep(double L, double A, double E){

        final double [][] a = {{1,0,-1,0},{0,0,0,0},{-1,0,1,0},{0,0,0,0}};
        Matrix b = new Matrix(a);
        double c = E*A/L ;
        Matrix Kep = new Matrix(4,4);
        Kep = b.times(c) ;

        return Kep;
    }

    public Matrix calcular_Le(double alfa){

        double [][] L = {{Math.cos(alfa),Math.sin(alfa),0,0},{-Math.sin(alfa),Math.cos(alfa),0,0},{0,0,Math.cos(alfa),Math.sin(alfa)},{0,0,-Math.sin(alfa),Math.cos(alfa)}};
        Matrix Le = new Matrix(L);
        return Le;
    }

    public static void archivo_datos(){

        NODOS1 = Datos_entrada.Datos.getNODOS();
        BARRAS1 = Datos_entrada.Datos.getBARRAS();
        APOYOS1 = Datos_entrada.Datos.getAPOYOS();
        CARGAS1 = Datos_entrada.Datos.getCARGAS();
    }


    public void Leer_datos(){

        NODOS1 = Datos_entrada.Datos.getNODOS();
        BARRAS1 = Datos_entrada.Datos.getBARRAS();
        APOYOS1 = Datos_entrada.Datos.getAPOYOS();
        CARGAS1 = Datos_entrada.Datos.getCARGAS();

        /*Matrix NODOS1 = GUI.Controller.getMatrixnodos();
        Matrix BARRAS1 = GUI.Controller.getMatrixbarras();
        Matrix APOYOS1 = GUI.Controller.getMatrixapoyos();
        Matrix CARGAS1 = GUI.Controller.getMatrixcargas();
        */

        //NODOS
        nodos_x = NODOS1;  // Matriz nodos_x
        n_nodos = NODOS1.getRowDimension();	// N�mero de nodos
        nodos_u = new Matrix(nodos_x.getRowDimension(),nodos_x.getColumnDimension());	// Matriz nodos_u



        //BARRAS
        n_elementos = BARRAS1.getRowDimension();  //N�mero de elementos
        elementos_A = new Matrix(n_elementos,1);
        elementos_E = new Matrix(n_elementos,1);
        elementos_nodos = new Matrix(n_elementos,2);

        for(int k=0;k<n_elementos;k++){

            elementos_A.set(k,0,BARRAS1.get(k, 3));
            elementos_E.set(k,0,BARRAS1.get(k,2));

            for(int n=0;n<2;n++){
                elementos_nodos.set(k, n, BARRAS1.get(k, n));
            }
        }

        elementos_N = new Matrix(n_elementos,1);



        //APOYOS
        n_apoyos = APOYOS1.getRowDimension();	//N�mero de apoyos
        Matrix apoyos_alfa = new Matrix(n_apoyos,1);
        apoyos_nodo = new Matrix(n_apoyos,1);
        apoyos_tipo = new Matrix(n_apoyos,1);

        for(int k=0;k<n_apoyos;k++){

            apoyos_alfa.set(k,0, APOYOS1.get(k, 2));
            apoyos_nodo.set(k,0, APOYOS1.get(k, 0));
            apoyos_tipo.set(k,0, APOYOS1.get(k, 1));
        }



        //CARGAS
        n_cargas = CARGAS1.getRowDimension();	//N�mero de cargas
        cargas_nodo = new Matrix(n_cargas,1);
        cargas_F = new Matrix(n_cargas,2);

        for(int k=0;k<n_cargas;k++){

            cargas_nodo.set(k, 0, CARGAS1.get(k,0));

            for(int n=1;n<3;n++){

                cargas_F.set(k, n-1, CARGAS1.get(k, n));
            }
        }


        /////////////////RESORTES///////////////



    }

    public void Ensamblar(){

        MK = new Matrix(2*n_nodos, 2*n_nodos);//Predimensionar K
        Matrix xi = new Matrix(1,2);
        Matrix xf = new Matrix(1,2);
        Matrix r = new Matrix(1,2);
        double L;
        double alfa;
        double A;
        double E;

        for(int k=0; k<n_elementos; k++){

            int ni = (int)elementos_nodos.get(k,0);
            int nf = (int)elementos_nodos.get(k,1);

            for(int n=0; n<2;n++){

                xi.set(0, n, nodos_x.get(ni-1,n));
                xf.set(0, n, nodos_x.get(nf-1,n));

            }

            r = xf.minus(xi);
            L = Math.sqrt(Math.pow(r.get(0, 0), 2)+Math.pow(r.get(0, 1), 2));
            alfa = Math.atan2(r.get(0,1), r.get(0, 0));
            A = elementos_A.get(k, 0);
            E = elementos_E.get(k, 0);

            // Calcular matriz rigidez elemental en coordenadas globales (Ke = Le^T * Ke' * Le)

            Matrix Kep;
            Kep = calcular_Kep(L, A, E);

            Matrix Le;
            Le = calcular_Le(alfa);

            Matrix Ke;
            Ke = (Le.transpose()).times(Kep).times(Le);
            double [][] pos_a= {{2*ni-1, 2*ni, 2*nf-1, 2*nf}};
            Matrix pos = new Matrix(pos_a);

            for(int v=0; v<(pos.getColumnDimension()); v++){

                for(int w=0; w<(pos.getColumnDimension()); w++){

                    MK.set((int)pos.get(0, v)-1, (int)pos.get(0, w)-1, (MK.get((int)pos.get(0,v)-1, (int)pos.get(0,w)-1)+Ke.get(v, w)));
                }
            }
        }
        // Calcular vectores de cargas nodales (F)

        MF = new Matrix(2*n_nodos, 1);//Predimensionar F
        Matrix f = new Matrix(2*n_nodos, 1);
        //Ensamblar vectores de cargas nodales

        for(int v=0; v<n_cargas; v++){
            int n= (int)cargas_nodo.get(v,0);
            for(int w=0; w<2;w++){
                f.set(w, 0, cargas_F.get(v, w));
            }
            double [][] pos2_a= {{2*n-1, 2*n}};
            Matrix pos2 = new Matrix(pos2_a);
            for(int w=0; w<2;w++){
                MF.set((int)pos2.get(0, w)-1, 0, (MF.get((int)pos2.get(0,w)-1, 0)+f.get(w, 0)));
            }
        }


    }

///////////////////////
//Modificar K para apoyos elasticos
///////////////////////

    public void Resolver(){

        Matrix GDLA= new Matrix(1,2*n_nodos);
        for(int k=0; k<2*n_nodos;k++){
            GDLA.set(0, k, k+1);
        }
        int n;
        for(int k=0; k<n_apoyos;k++){
            n= (int)apoyos_nodo.get(k, 0);

            switch((int)apoyos_tipo.get(k, 0)){

                case 1:
                    GDLA.set(0, (2*n-1)-1, 0);
                    GDLA.set(0, (2*n)-1, 0);
                    break;

                case 2:
                    GDLA.set(0, (2*n)-1, 0);
                    break;

                case 3:
                    GDLA.set(0, (2*n-1)-1, 0);
                    break;
            }
        }
        int m=0;
        int h=0;
        for(int k=0; k<2*n_nodos;k++){
            if(GDLA.get(0,k)!=0){
                h=h+1;
            }
        }

        Matrix GDLA2 = new Matrix(1,h);

        for(int k=0; k<2*n_nodos;k++){
            if(GDLA.get(0,k)!=0){
                GDLA2.set(0, m, GDLA.get(0, k));
                m=m+1;
            }
        }

        Matrix MK2 = new Matrix(GDLA2.getColumnDimension(),GDLA2.getColumnDimension());
        Matrix MF2 = new Matrix(GDLA2.getColumnDimension(),1);
        sol = new Matrix(GDLA2.getColumnDimension(),1);
        for(m=0; m<GDLA2.getColumnDimension();m++){
            for(n=0; n<GDLA2.getColumnDimension();n++){
                MK2.set(m, n, MK.get((int)GDLA2.get(0, m)-1, (int)GDLA2.get(0, n)-1));
            }
        }
        for(m=0; m<GDLA2.getColumnDimension();m++){
            MF2.set(m, 0, MF.get((int)GDLA2.get(0, m)-1, 0));
        }

        sol=MK2.solve(MF2); //sol2=MK2.inverse().times(MF2);

        //Copiar soluci�n a matriz nodos_u
        int contador=0;
        int contador2=0;
        for(int k=0;k<n_nodos;k++){
            for(h=0;h<2;h++){
                if(GDLA.get(0, contador2)==0){
                    nodos_u.set(k, h, 0);
                    contador2=contador2+1;
                }else{
                    nodos_u.set(k, h, sol.get(contador, 0));
                    contador=contador+1;
                    contador2=contador2+1;
                }
            }
        }

        //Calcular reacciones en los apoyos
        Matrix MK3 = new Matrix(2*n_nodos,GDLA2.getColumnDimension());
        for(int k=0;k<2*n_nodos;k++){
            for(h=0;h<GDLA2.getColumnDimension();h++){
                MK3.set(k, h, MK.get(k, (int)GDLA2.get(0, h)-1));
            }
        }
        apoyos_R = new Matrix(n_apoyos,2);
        Matrix VARM = new Matrix(1,1);
        Matrix VARM2 = new Matrix(1,1);
        for(int k=0;k<n_apoyos;k++){
            n= (int)apoyos_nodo.get(k, 0);
            switch((int)apoyos_tipo.get(k, 0)){

                case 1:
                    VARM = MK3.getMatrix((2*n-1)-1, (2*n-1)-1, 0, GDLA2.getColumnDimension()-1).times(sol).times(-1);
                    VARM2 = MK3.getMatrix((2*n)-1, (2*n)-1, 0, GDLA2.getColumnDimension()-1).times(sol).times(-1);
                    apoyos_R.set(k, 0, VARM.get(0,0));
                    apoyos_R.set(k, 1, VARM2.get(0,0));
                    break;

                case 2:
                    VARM2 = MK3.getMatrix((2*n)-1, (2*n)-1, 0, GDLA2.getColumnDimension()-1).times(sol).times(-1);
                    apoyos_R.set(k, 1, VARM2.get(0,0));
                    break;

                case 3:
                    VARM = MK3.getMatrix((2*n-1)-1, (2*n-1)-1, 0, GDLA2.getColumnDimension()-1).times(sol).times(-1);
                    apoyos_R.set(k, 0, VARM.get(0,0));
                    break;
                ////poner caso 5 elastico///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            }
        }

        //Calcular esfuerzos de barras

        Matrix xi = new Matrix(1,2);
        Matrix xf = new Matrix(1,2);
        Matrix r = new Matrix(1,2);
        double L;
        double alfa;
        double A;
        double E;

        for(int k=0; k<n_elementos; k++){

            int ni = (int)elementos_nodos.get(k,0);
            int nf = (int)elementos_nodos.get(k,1);

            for(n=0; n<2;n++){

                xi.set(0, n, nodos_x.get(ni-1,n));
                xf.set(0, n, nodos_x.get(nf-1,n));

            }

            r = xf.minus(xi);
            L = Math.sqrt(Math.pow(r.get(0, 0), 2)+Math.pow(r.get(0, 1), 2));
            alfa = Math.atan2(r.get(0,1), r.get(0, 0));
            A = elementos_A.get(k, 0);
            E = elementos_E.get(k, 0);

            Matrix Kep;
            Kep = calcular_Kep(L, A, E);

            Matrix Le;
            Le = calcular_Le(alfa);

            double[][] u1= {{nodos_u.get(ni-1,0)},{nodos_u.get(ni-1,1)},{nodos_u.get(nf-1,0)},{nodos_u.get(nf-1,1)}};
            Matrix u = new Matrix(u1);
            Matrix up = Le.times(u);
            Matrix fp = Kep.times(up);
            elementos_N.set(k,0,fp.get(2,0));
        }






    }

    public static Matrix devolver(){
        return nodos_u;
    }
    public static Matrix devolverReacciones(){
        return apoyos_R;
    }
    public static Matrix devolverElementosN(){
        return elementos_N;
    }
    public static Matrix devolverElementosA(){
        return elementos_A;
    }

}
