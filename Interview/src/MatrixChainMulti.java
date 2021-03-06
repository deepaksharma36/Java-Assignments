/**
* @(#)MatrixChainMulti.java
*
*
* @author   Koushik
* @version 1.00 2012/10/4
*/

    public class MatrixChainMulti {
    protected int m[][];
    protected int s[][];
    protected int n;
    public void matrixChainOrder(int[] p){
    n=p.length-1;
    m=new int[n][n];
    s=new int[n][n];
    for(int i=0;i<n;i++){
    m[i]=new int[n];
    m[i][i]=0;
    s[i]=new int[n];
    }
    for(int l=1;l<n;l++){
    for(int i=0;i<n-l;i++){
    int j=i+l;
    m[i][j]=Integer.MAX_VALUE;
    for(int k=i;k<j;k++){
    int q=m[i][k]+m[k+1][j]+p[i]*p[k+1]*p[j+1];
    if(q<m[i][j]){
    m[i][j]=q;
    s[i][j]=k;
    }
    }
    }
    }
    for (int ii=0;ii<n;ii++){
    	for(int jj=0;jj<n;jj++)
    	{
    		System.out.print(m[ii][jj]+"\t");
    		
    	}
    System.out.println();
    }
    
    System.out.println("*******************");
    for (int ii=0;ii<n;ii++){
    	for(int jj=0;jj<n;jj++)
    	{
    		System.out.print(s[ii][jj]+"\t");
    	}
    System.out.println();}
    
    }
    void printOptimalParens(){
    printOptimalParens(s,0,n-1);
    }
    void printOptimalParens(int[][]s, int i, int j) {
    if(i==j){
    System.out.print("A"+"<"+i );
    }
    else{
    System.out.print(" ( ");
    printOptimalParens(s,i,s[i][j]);
    printOptimalParens(s,s[i][j]+1,j);
    System.out.print(" ) ");
    }
    }
    public static void main (String[] args) {
    MatrixChainMulti mcm=new MatrixChainMulti();
    int[] p={20,6,50,10,3,20,25};
    try{
    mcm.matrixChainOrder(p);
    mcm.printOptimalParens();
    }catch(Exception e){
    e.printStackTrace();
    }
    }
    }     //end of code
