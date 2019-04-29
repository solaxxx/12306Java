package per.solax.gradle;

/**
 * @Author: solax
 * @Date: 2019/1/10
 */
public class GradleExec {

    Initialize initialize;

    Runner runner;

    public GradleExec () {
        assembly ();
    }


    private void assembly () {
        this.initialize = new Initialize();
        this.runner = new Runner();
    }

    public void run () {
        this.runner.run();
    }
}
