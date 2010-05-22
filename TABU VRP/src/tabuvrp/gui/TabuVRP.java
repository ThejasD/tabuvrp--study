package tabuvrp.gui;

import tabuvrp.core.Graph;
import tabuvrp.core.Solution;
import tabuvrp.core.stage.Stage;
import tabuvrp.core.stage.StageListener;
import tabuvrp.tabustage.TabuStage;
import tabuvrp.tabustage.BasicTabuStageParams;


public class TabuVRP extends javax.swing.JFrame implements StageListener {

    /** Creates new form TabuVRP */
    public TabuVRP(Graph graph) {
        this.graph = graph;
        n = graph.getNodeCount();
        mt = graph.getMTilde();
        /* SOLUTION INIT */
        sol = new Solution(graph);
        for(int i = 1; i < n; ++i) {
            sol.makePath(i);
        }
        initComponents();
        initS0();
        tabuStageDisplay1.clear();
        tabuStageDisplay2.clear();
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabuStageDisplay1 = new tabuvrp.gui.TabuStageDisplay();
        tabuStageDisplay2 = new tabuvrp.gui.TabuStageDisplay();
        runButton = new javax.swing.JButton();
        solutionDisplay1 = new tabuvrp.gui.SolutionDisplay(graph, sol);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tabu Search VRP");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Tabu Stages"));

        tabuStageDisplay1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Stage 1"));

        tabuStageDisplay2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Stage 2"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabuStageDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabuStageDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tabuStageDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tabuStageDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(runButton)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solutionDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(solutionDisplay1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(runButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        runButton.setEnabled(false);
        tabuStageDisplay1.clear();
        tabuStageDisplay2.clear();
        new Thread() {
                @Override
                public void run() {
                    s0.runStage();
                }
            }.start();
    }//GEN-LAST:event_runButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton runButton;
    private tabuvrp.gui.SolutionDisplay solutionDisplay1;
    private tabuvrp.gui.TabuStageDisplay tabuStageDisplay1;
    private tabuvrp.gui.TabuStageDisplay tabuStageDisplay2;
    // End of variables declaration//GEN-END:variables
    private final Graph graph;
    private final int n;
    private final double mt;
    private Solution sol;
    private BasicTabuStageParams s0params;
    private BasicTabuStageParams s1params;
    private TabuStage s0;
    private TabuStage s1;

    public void stageStarted(Stage stage) {
    }

    public void stepDone(Stage stage) {
    }

    public void newBestSolution(Stage stage) {
        solutionDisplay1.setSolution(stage.getBestSolution());
    }

    public void stageStopped(Stage stage) {
        System.out.println(stage.getBestSolution() + "\n");
        if (stage == s0) {
            initS1();
            new Thread() {
                @Override
                public void run() {
                    s1.runStage();
                }
            }.start();
        }
        else if (stage == s1) {
            initS0();
            System.gc();
            runButton.setEnabled(true);
        }
    }

    private void initS0() {
        /* SOLUTION INIT */
        sol = new Solution(graph);
        for(int i = 1; i < n; ++i) {
            sol.makePath(i);
        }
        /* STAGE 0 */
        s0params = new BasicTabuStageParams(sol,
                    1, 10,                                  // alpha, beta
                    Math.min(n, 5),                         // p
                    (int) Math.min(n, Math.round(5 * mt)),  // q
                    5, 10,                                  // min theta, max theta
                    50 * n);

        s0 = new TabuStage(graph, s0params, sol);
        s0.addTabuStageListener(tabuStageDisplay1);
        s0.addStageListener(this);
    }


    private void initS1() {
        /* STAGE 1 */
        sol = s0.getBestSolution();
        s1params = new BasicTabuStageParams(sol,
                    1, 10,
                    Math.min(n, 10),
                    n,
                    5, 10,
                    100);
        s1 = new TabuStage(graph, s1params, sol);
        s1.addTabuStageListener(tabuStageDisplay2);
        s1.addStageListener(this);
    }
}
