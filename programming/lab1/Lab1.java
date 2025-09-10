import java.lang.Math;

public class Lab1 {
    public static void main(String[] args) {
        short[] c = {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};//1 task
        float[] x = new float[14];
        for (int i = 0;i < x.length;i++){
            x[i]= (float) (Math.random()*17)-7;
        }//2 task
        double[][] dvmass = new double[17][14];
        for (int i =0;i<17;i++){
            for (int j =0;j<14;j++){
                if (c[i]==17) {
                    dvmass[i][j] = Math.sin(Math.sin(Math.tan(x[j])));
                }
                else if(c[i]==5 || c[i]==6 || c[i]==8 || c[i]==10 || c[i]==13 || c[i]==15 || c[i]==19) {
                    dvmass[i][j]= Math.pow((Math.pow((3*Math.pow(x[j],1/3)),3)),1/3);
                }
                else{
                    dvmass[i][j] = Math.sin(0.25*(Math.cos(Math.exp(x[j]))+3/4));
                }//3 task
            }
        }
        System.out.print("[");
        for (int i =0;i<17;i++){
            System.out.print("[");
            for (int j =0;j<14;j++){
                System.out.printf("%.2f\t",dvmass[i][j]);
            }
            if (i==16){
                System.out.print("]");
                break;
            }
            System.out.println("],");
        }//4 task
    }
}
