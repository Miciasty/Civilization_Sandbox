package src.main.java.nsk.Resources.Entities;

import src.main.java.nsk.Resources.Entity;

public class Knight extends Entity {

    private JobType job = JobType.Trainee;

    public Knight(int h, int d, JobType job) {
        this.setHealth(h);
        this.setDamage(d);
        this.setJob(job);
    }

    private void setJob(JobType j) {
        this.job = j;
    }
    public JobType getJob() {
        return this.job;
    }

    public enum JobType {
        Trainee,
        Warrior,
        Bowman,
        Horseman,
        Magician
    }

}
