public class Enemy_Skeleton extends Enemy {
    public Enemy_Skeleton(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Skeleton appeared.");
    }
    
    //Skeletons do not have anything special so they use the basic Entity methods
}
