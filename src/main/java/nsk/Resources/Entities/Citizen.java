package src.main.java.nsk.Resources.Entities;

import src.main.java.nsk.Resources.Entity;

public class Citizen extends Entity {

    private JobType job = JobType.Homeless;

    public Citizen(int h, int d,JobType job) {
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
        Homeless,
        JOB2,
        JOB3
    }

}
