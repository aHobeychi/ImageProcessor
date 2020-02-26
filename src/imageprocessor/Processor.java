/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessor;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 *
 *
 */
public class Processor extends javax.swing.JFrame {

    /**
     * Creates new form Processor
     */
    boolean modificationsDone = false;
    BufferedImage img;
    BufferedImage output1;
    int[][] ogPixelMatrix;
    Color[][] ogColorMatrix;
    Color[][] colorMatrix;
    float[][] ogSaturationMatrix;
    Color[][] brightnessMatrix;
    String fileType;
    int[][] averageRGB;
    final float gaussianKernel[][] = new float[][]{
        {0.00000067f, 0.00002292f, 0.00019117f, 0.00038771f, 0.00019117f, 0.00002292f, 0.00000067f},
        {0.00002292f, 0.00078634f, 0.00655965f, 0.01330373f, 0.00655965f, 0.00078633f, 0.00002292f},
        {0.00019117f, 0.00655965f, 0.05472157f, 0.11098164f, 0.05472157f, 0.00655965f, 0.00019117f},
        {0.00038771f, 0.01330373f, 0.11098164f, 0.22508352f, 0.11098164f, 0.01330373f, 0.00038771f},
        {0.00019117f, 0.00655965f, 0.05472157f, 0.11098164f, 0.05472157f, 0.00655965f, 0.00019117f},
        {0.00002292f, 0.00078634f, 0.00655965f, 0.01330373f, 0.00655965f, 0.00078633f, 0.00002292f},
        {0.00000067f, 0.00002292f, 0.00019117f, 0.00038771f, 0.00019117f, 0.00002292f, 0.00000067f}
    };
    final float edgeDetectionKernel[][] = new float[][]{
        {-1f, -1f, -1f},
        {-1f, 8f, -1f},
        {-1f, -1f, -1f},};
    final float boxBlurKernel[][] = new float[][]{
        {0.1111111f, 0.1111111f, 0.1111111f},
        {0.1111111f, 0.1111111f, 0.1111111f},
        {0.1111111f, 0.1111111f, 0.1111111f},};
    final float sharpenKernel[][] = new float[][]{
        {0f, -1f, 0f},
        {-1f, 5f, -1f},
        {0f, -1f, 0f},};

    String str;
    boolean wasModified = false;

    public Processor() {
        initComponents();
        setIcons();
        resetButton.doClick();
         messageArea.setText("");
        disableAll();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        bwLabel = new javax.swing.JLabel();
        brightnessLabel = new javax.swing.JLabel();
        saturationLabel = new javax.swing.JLabel();
        sharpnessLabel = new javax.swing.JLabel();
        rtLabel = new javax.swing.JLabel();
        gtLabel = new javax.swing.JLabel();
        btLabel = new javax.swing.JLabel();
        bwSlider = new javax.swing.JSlider();
        brightSlider = new javax.swing.JSlider();
        gammaCorrectionSlider = new javax.swing.JSlider();
        saturationSlider = new javax.swing.JSlider();
        sharpSlider = new javax.swing.JSlider();
        rtSlider = new javax.swing.JSlider();
        gtSlider = new javax.swing.JSlider();
        btSlider = new javax.swing.JSlider();
        originalImagePanel = new imageprocessor.ImagePanel();
        pictureFrame = new imageprocessor.ImagePanel();
        filterFrame = new javax.swing.JPanel();
        bFilterCheckBox = new java.awt.Checkbox();
        gaussianFilterCheckBox = new java.awt.Checkbox();
        edfCheckBox = new java.awt.Checkbox();
        greyscCheckBox = new java.awt.Checkbox();
        resetButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        gammaCorrectionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        openFileButton = new javax.swing.JMenuItem();
        saveButton = new javax.swing.JMenuItem();
        exitButton = new javax.swing.JMenuItem();
        viewButton = new javax.swing.JMenu();
        changeSizeButton = new javax.swing.JMenuItem();
        flipButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bwLabel.setText("Black & White / Color");

        brightnessLabel.setText("Brightness");

        saturationLabel.setText("Saturation");

        sharpnessLabel.setText("Sharpness");

        rtLabel.setText("Red Tint");

        gtLabel.setText("Green Tint");

        btLabel.setText("Blue Tint");

        bwSlider.setMaximum(255);
        bwSlider.setMinimum(-255);
        bwSlider.setValue(0);
        bwSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bwSliderStateChanged(evt);
            }
        });

        brightSlider.setMaximum(255);
        brightSlider.setMinimum(-255);
        brightSlider.setValue(0);
        brightSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                brightSliderStateChanged(evt);
            }
        });

        gammaCorrectionSlider.setMinimum(-100);
        gammaCorrectionSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gammaCorrectionSliderStateChanged(evt);
            }
        });

        saturationSlider.setMaximum(1);
        saturationSlider.setMinimum(-1);
        saturationSlider.setValue(0);
        saturationSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                saturationSliderStateChanged(evt);
            }
        });

        sharpSlider.setMaximum(254);
        sharpSlider.setValue(127);

        rtSlider.setMaximum(255);
        rtSlider.setMinimum(-255);
        rtSlider.setToolTipText("");
        rtSlider.setValue(0);
        rtSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rtSliderStateChanged(evt);
            }
        });

        gtSlider.setMaximum(255);
        gtSlider.setMinimum(-255);
        gtSlider.setSnapToTicks(true);
        gtSlider.setValue(0);
        gtSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gtSliderStateChanged(evt);
            }
        });

        btSlider.setMaximum(255);
        btSlider.setMinimum(-255);
        btSlider.setValue(0);
        btSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                btSliderStateChanged(evt);
            }
        });

        originalImagePanel.setBackground(new java.awt.Color(204, 204, 204));
        originalImagePanel.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout originalImagePanelLayout = new javax.swing.GroupLayout(originalImagePanel);
        originalImagePanel.setLayout(originalImagePanelLayout);
        originalImagePanelLayout.setHorizontalGroup(
            originalImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );
        originalImagePanelLayout.setVerticalGroup(
            originalImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        pictureFrame.setBackground(new java.awt.Color(204, 204, 204));
        pictureFrame.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout pictureFrameLayout = new javax.swing.GroupLayout(pictureFrame);
        pictureFrame.setLayout(pictureFrameLayout);
        pictureFrameLayout.setHorizontalGroup(
            pictureFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
        );
        pictureFrameLayout.setVerticalGroup(
            pictureFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        filterFrame.setBackground(new java.awt.Color(204, 204, 204));
        filterFrame.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        filterFrame.setForeground(new java.awt.Color(102, 102, 102));

        bFilterCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bFilterCheckBox.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        bFilterCheckBox.setLabel("Box Filter");

        gaussianFilterCheckBox.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        gaussianFilterCheckBox.setLabel("Gaussian Filter");

        edfCheckBox.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        edfCheckBox.setLabel("Edge Detection Filter");

        greyscCheckBox.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        greyscCheckBox.setLabel("Convert To Grey Scale");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filterFrameLayout = new javax.swing.GroupLayout(filterFrame);
        filterFrame.setLayout(filterFrameLayout);
        filterFrameLayout.setHorizontalGroup(
            filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gaussianFilterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edfCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bFilterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greyscCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(applyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        filterFrameLayout.setVerticalGroup(
            filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bFilterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gaussianFilterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(applyButton))
                .addGroup(filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filterFrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edfCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greyscCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(filterFrameLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(resetButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        gammaCorrectionLabel.setText("Gamma Correction");

        messageArea.setColumns(20);
        messageArea.setRows(5);
        jScrollPane1.setViewportView(messageArea);

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        file.setText("File");

        openFileButton.setText("New File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });
        file.add(openFileButton);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        file.add(saveButton);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        file.add(exitButton);

        jMenuBar1.add(file);

        viewButton.setText("Edit");

        changeSizeButton.setText("Adjust Size");
        changeSizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSizeButtonActionPerformed(evt);
            }
        });
        viewButton.add(changeSizeButton);

        flipButton.setText("Flip 90 degrees");
        flipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flipButtonActionPerformed(evt);
            }
        });
        viewButton.add(flipButton);

        jMenuBar1.add(viewButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brightnessLabel)
                    .addComponent(bwLabel)
                    .addComponent(saturationLabel)
                    .addComponent(sharpnessLabel)
                    .addComponent(rtLabel)
                    .addComponent(gtLabel)
                    .addComponent(btLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .addComponent(brightSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bwSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saturationSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sharpSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rtSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gtSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(filterFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gammaCorrectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gammaCorrectionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(originalImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(bwLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bwSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(brightnessLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saturationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saturationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sharpnessLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sharpSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rtSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gtLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gtSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gammaCorrectionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gammaCorrectionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filterFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(originalImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
 
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("C:\\Users\\alexh\\Pictures\\Saved Pictures"));
            int returnVal = chooser.showOpenDialog(Processor.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                pictureFrame.setImage(chooser.getSelectedFile().getAbsolutePath());
                originalImagePanel.setImage(chooser.getSelectedFile().getAbsolutePath());
                str = chooser.getSelectedFile().getAbsolutePath();
                img = pictureFrame.getBufferedImage();



            }
            processPicture();
            brightnessMatrix();
            messageArea.setText("The picture was succesfully opened");
            enableAll();
        } catch (Exception e) {
            messageArea.setText("The final was unable to open");
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        BufferedImage img = pictureFrame.getBufferedImage();
        if (modificationsDone == true) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("C:\\Users\\alexh\\Pictures\\Saved Pictures"));
            chooser.setApproveButtonText("Save");
            chooser.setDialogTitle("Save");
            chooser.setCurrentDirectory(new File("/images"));
            int returnVal = chooser.showOpenDialog(Processor.this);
            if (returnVal == JFileChooser.APPROVE_OPTION && wasModified == true) {
                try {
                    ImageIO.write(img, "png", chooser.getSelectedFile());
                    messageArea.setText("The file was succefully saved");
                } catch (Exception e) {
                    messageArea.setText("The file was unable to save");
                }
            }
        } else {
            messageArea.setText("Could not save because the \nimage was never modified \nor was not loaded.");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void changeSizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSizeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_changeSizeButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        // System.exit(0);
        exitRequest();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void bwSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bwSliderStateChanged
        // TODO add your handling code here:
        bwChange(bwSlider.getValue());
        modificationsDone = true;
    }//GEN-LAST:event_bwSliderStateChanged

    private void rtSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rtSliderStateChanged

        rtChange(rtSlider.getValue());
        modificationsDone = true;
    }//GEN-LAST:event_rtSliderStateChanged

    private void gtSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gtSliderStateChanged
       
        gtChange(gtSlider.getValue());
        modificationsDone = true;
    }//GEN-LAST:event_gtSliderStateChanged

    private void btSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btSliderStateChanged
 

        int value = btSlider.getValue();
        btChange(value);
        modificationsDone = true;
    }//GEN-LAST:event_btSliderStateChanged

    private void saturationSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_saturationSliderStateChanged
      
        saturationChange(saturationSlider.getValue());
        modificationsDone = true;
    }//GEN-LAST:event_saturationSliderStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
       

        try {
            bwSlider.setValue(0);
            rtSlider.setValue(0);
            gtSlider.setValue(0);
            btSlider.setValue(0);
            brightSlider.setValue(0);
            saturationSlider.setValue(0);
            gammaCorrectionSlider.setValue(0);
            pictureFrame.setImage(str);
            img = pictureFrame.getBufferedImage();
            modificationsDone = false;
            messageArea.setText("All was succefully reset.");
        } catch (Exception e) {
            messageArea.setText("Couldnt Reset");
        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private void brightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_brightSliderStateChanged
      
        brigthnessChange(brightSlider.getValue());
    }//GEN-LAST:event_brightSliderStateChanged

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
    
        try {
            String filterApplied = "No Filter Applied";

            if (bFilterCheckBox.getState()) {
                ApplyConvolutionFilter(boxBlurKernel);
                filterApplied = "Box Blur Applied";
            }

            if (gaussianFilterCheckBox.getState()) {
                ApplyConvolutionFilter(gaussianKernel);
                filterApplied += "\nGaussian filter Applied";
            }

            if (edfCheckBox.getState()) {
                ApplyConvolutionFilter(edgeDetectionKernel);
                filterApplied += "\nEdge Detection Applied Applied";
            }
            if (greyscCheckBox.getState()) {
                convertToGreyScale();
                filterApplied += "\nGrey Scale Applied Applied";
            }
            modificationsDone = true;
            messageArea.setText(filterApplied);
        } catch (Exception e) {
            messageArea.setText("Could not Apply the filters because \nno image was selected.");
        }
        processPicture();
    }//GEN-LAST:event_applyButtonActionPerformed

    private void flipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flipButtonActionPerformed
        // TODO add your handling code here:

        BufferedImage img = pictureFrame.getBufferedImage();
        messageArea.setText(img.getWidth() + " W" + img.getHeight() + " H");

        BufferedImage flipped = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                flipped.setRGB(y, x, img.getRGB(x, y));

            }
        }
        pictureFrame.setImage(flipped);
        modificationsDone = true;
    }//GEN-LAST:event_flipButtonActionPerformed

    private void gammaCorrectionSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gammaCorrectionSliderStateChanged
       
        changeGamma(gammaCorrectionSlider.getValue());
        modificationsDone = true;
    }//GEN-LAST:event_gammaCorrectionSliderStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Processor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Processor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Processor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Processor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Processor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JButton applyButton;
    private java.awt.Checkbox bFilterCheckBox;
    private javax.swing.JSlider brightSlider;
    private javax.swing.JLabel brightnessLabel;
    private javax.swing.JLabel btLabel;
    private javax.swing.JSlider btSlider;
    private javax.swing.JLabel bwLabel;
    private javax.swing.JSlider bwSlider;
    private javax.swing.JMenuItem changeSizeButton;
    private java.awt.Checkbox edfCheckBox;
    private javax.swing.JMenuItem exitButton;
    private javax.swing.JMenu file;
    private javax.swing.JPanel filterFrame;
    private javax.swing.JMenuItem flipButton;
    private javax.swing.JLabel gammaCorrectionLabel;
    private javax.swing.JSlider gammaCorrectionSlider;
    private java.awt.Checkbox gaussianFilterCheckBox;
    private java.awt.Checkbox greyscCheckBox;
    private javax.swing.JLabel gtLabel;
    private javax.swing.JSlider gtSlider;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JMenuItem openFileButton;
    private imageprocessor.ImagePanel originalImagePanel;
    private imageprocessor.ImagePanel pictureFrame;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel rtLabel;
    private javax.swing.JSlider rtSlider;
    private javax.swing.JLabel saturationLabel;
    private javax.swing.JSlider saturationSlider;
    private javax.swing.JMenuItem saveButton;
    private javax.swing.JSlider sharpSlider;
    private javax.swing.JLabel sharpnessLabel;
    private javax.swing.JMenu viewButton;
    // End of variables declaration//GEN-END:variables

    private void setIcons() {
        ImageIcon fileIcon = new ImageIcon(getClass().getClassLoader().getResource("Icons//New_File_Icon.png"));
        Image fileImage = fileIcon.getImage();
        Image resized = fileImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        openFileButton.setIcon(new ImageIcon(resized));

        ImageIcon OpenIcon = new ImageIcon(getClass().getClassLoader().getResource("Icons//Open_Icon.png"));
        Image OpenImage = OpenIcon.getImage();
        Image resizedOI = OpenImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);

        ImageIcon SaveIcon = new ImageIcon(getClass().getClassLoader().getResource("Icons//Save_Icon.png"));
        Image SaveImage = SaveIcon.getImage();
        Image resizedSave = SaveImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        saveButton.setIcon(new ImageIcon(resizedSave));

        ImageIcon ExitIcon = new ImageIcon(getClass().getClassLoader().getResource("Icons//Exit_Icon.png"));
        Image ExitImage = ExitIcon.getImage();
        Image resizedExit = ExitImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(resizedExit));

    }

    private void exitRequest() {
        int reply = JOptionPane.showConfirmDialog(Processor.this, "Are you sure you want to close?", "Close?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void btChange(int value) {

        try {

            BufferedImage img = pictureFrame.getBufferedImage();

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb >> 0) & 0xFF;

                    //if (ogColorMatrix[y][x].getBlue() < 255 - value && ogColorMatrix[y][x].getBlue() - Math.abs(value) > 0) {
                    blue = ogColorMatrix[y][x].getBlue() + value;
                    //}

                    if (blue < 0) {
                        blue = 0;
                    } else if (blue > 255) {
                        blue = 255;
                    }

                    rgb = alpha << 24 | red << 16 | green << 8 | blue << 0;

                    img.setRGB(x, y, rgb);

                }
            }
            pictureFrame.repaint();

        } catch (Exception e) {
            messageArea.setText("");
        }
        brightnessMatrix();
        averageMatrix();
        wasModified = true;
    }

    private void processPicture() {
        BufferedImage img = pictureFrame.getBufferedImage();
        ogPixelMatrix = new int[img.getHeight()][img.getWidth()];
        ogColorMatrix = new Color[img.getHeight()][img.getWidth()];
        ogSaturationMatrix = new float[img.getHeight()][img.getWidth()];
        brightnessMatrix = new Color[img.getHeight()][img.getWidth()];
        averageRGB = new int[img.getHeight()][img.getWidth()];
        colorMatrix = new Color[img.getHeight()][img.getWidth()];

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                ogColorMatrix[y][x] = new Color(img.getRGB(x, y));
                ogPixelMatrix[y][x] = img.getRGB(x, y);
                colorMatrix[y][x] = new Color(img.getRGB(x, y));
            }

        }

    }

    private void rtChange(int value) {

        try {

            // Convert image to black and white
            BufferedImage img = pictureFrame.getBufferedImage();

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb >> 0) & 0xFF;

                    red = ogColorMatrix[y][x].getRed() + value;

                    if (red > 255) {
                        red = 255;
                    } else if (red < 0) {
                        red = 0;
                    }

                    rgb = alpha << 24 | red << 16 | green << 8 | blue << 0;

                    img.setRGB(x, y, rgb);

                }
            }
            pictureFrame.repaint();

        } catch (Exception e) {
            messageArea.setText("Could not Execute Current Operation");
        }
        brightnessMatrix();
        wasModified = true;

    }

    private void gtChange(int value) {

        try {

            // Convert image to black and white
            BufferedImage img = pictureFrame.getBufferedImage();

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb >> 0) & 0xFF;

                    green = ogColorMatrix[y][x].getGreen() + value;

                    if (green > 255) {
                        green = 255;
                    } else if (green < 0) {
                        green = 0;
                    }
                    rgb = alpha << 24 | red << 16 | green << 8 | blue << 0;
                    System.out.println(rgb);

                    img.setRGB(x, y, rgb);

                }
            }
            pictureFrame.repaint();

        } catch (Exception e) {
            messageArea.setText("");
        }
        brightnessMatrix();
        wasModified = true;

    }

    private void saturationChange(int value) {

        BufferedImage outputImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        try {

            float val = (float) value * 0.01f;

            BufferedImage img = pictureFrame.getBufferedImage();

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb; //always returns TYPE_INT_ARGB

                    Color colorValue = new Color(img.getRGB(x, y));

                    float[] hsb = Color.RGBtoHSB(ogColorMatrix[y][x].getRed(), ogColorMatrix[y][x].getGreen(), ogColorMatrix[y][x].getBlue(), null);

                    float[] hsbOrig = Color.RGBtoHSB(ogColorMatrix[y][x].getRed(), ogColorMatrix[y][x].getGreen(), ogColorMatrix[y][x].getBlue(), null);

                    hsb[1] = hsbOrig[1] + val;

                    if (hsb[1] > 1) {
                        hsb[1] = 0.99999999999999f;
                    } else if (hsb[1] <= 0) {
                        hsb[1] = 0.00001f;
                    }

                    rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    outputImage.setRGB(x, y, rgb);
                }
            }

            for (int j = 0; j < img.getHeight(); ++j) {
                for (int i = 0; i < img.getWidth(); ++i) {

                    int rgb = outputImage.getRGB(i, j);

                    img.setRGB(i, j, rgb);
                }
            }
            pictureFrame.repaint();
            wasModified = true;

        } catch (Exception e) {
            messageArea.setText("Could not Execute Current Operation");
        }
    }

    private void brigthnessChange(int value) {
        try {

            // Convert image to black and white
            BufferedImage img = pictureFrame.getBufferedImage();

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb >> 0) & 0xFF;

                    red = brightnessMatrix[y][x].getRed() + value;
                    blue = brightnessMatrix[y][x].getBlue() + value;
                    green = brightnessMatrix[y][x].getGreen() + value;

                    if (red > 255) {
                        red = 255;
                    } else if (red < 0) {
                        red = 0;
                    }
                    if (blue > 255) {
                        blue = 255;
                    } else if (blue < 0) {
                        blue = 0;
                    }
                    if (green > 255) {
                        green = 255;
                    } else if (green < 0) {
                        green = 0;
                    }
                    rgb = alpha << 24 | red << 16 | green << 8 | blue << 0;

                    img.setRGB(x, y, rgb);
                }
            }
            pictureFrame.repaint();
            wasModified = true;

        } catch (Exception e) {
            messageArea.setText("Could not Execute Current Operation");
        }
    }

    private void bwChange(int value) {
        try {
            BufferedImage img = pictureFrame.getBufferedImage();
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb >> 0) & 0xFF;
                    int redInitial = red;
                    int greenInitial = green;
                    int blueInitial = blue;

                    // Modifying RED.
                    if (value > 0) {
                        if (averageRGB[y][x] > red) {
                            red = ogColorMatrix[y][x].getRed() + value;

                        } else if (averageRGB[y][x] < red) {
                            red = ogColorMatrix[y][x].getRed() - value;
                        }
                        if (red > 255 && redInitial < averageRGB[y][x]) {
                            red = averageRGB[y][x];
                        }
                        if (red < 0 && redInitial > averageRGB[y][x]) {
                            red = averageRGB[y][x];
                        }

                        // Modifying Green.
                        if (averageRGB[y][x] > green) {
                            green = ogColorMatrix[y][x].getGreen() + value;

                        } else if (averageRGB[y][x] < green) {
                            green = ogColorMatrix[y][x].getGreen() - value;

                        }

                        if (green > 255 && greenInitial < averageRGB[y][x]) {
                            green = averageRGB[y][x];
                        }
                        if (green < 0 && greenInitial > averageRGB[y][x]) {
                            green = averageRGB[y][x];
                        }

                        // Modifying blue.
                        if (averageRGB[y][x] > blue) {
                            blue = ogColorMatrix[y][x].getBlue() + value;

                        } else if (averageRGB[y][x] < blue) {
                            blue = ogColorMatrix[y][x].getBlue() - value;
                        }
                        if (blue > 255 && blueInitial < averageRGB[y][x]) {
                            blue = averageRGB[y][x];
                        }
                        if (blue < 0 && blueInitial > averageRGB[y][x]) {
                            blue = averageRGB[y][x];
                        }

                    }
                    rgb = alpha << 24 | red << 16 | green << 8 | blue << 0;

                    img.setRGB(x, y, rgb);
                }
            }
            pictureFrame.repaint();
            wasModified = true;
        } catch (Exception e) {
            messageArea.setText("Could not Execute Current Operation");

        }
    }

    private void brightnessMatrix() {
        try {
            BufferedImage img = pictureFrame.getBufferedImage();
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    brightnessMatrix[y][x] = new Color(img.getRGB(x, y));
                }
            }
        } catch (Exception e) {
            messageArea.setText("Could not Execute Current Operation");
        }

    }

    private void averageMatrix() {

        try {

            // Convert image to black and white
            BufferedImage img = pictureFrame.getBufferedImage();

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb >> 0) & 0xFF;

                    averageRGB[y][x] = (red + green + blue) / 3;
                }
            }
        } catch (Exception e) {

        }
    }

    private int WrapVerticalIndex(int i) {
        // This takes care of negative and positive indices beyond the image resolution
        return (i + img.getHeight()) % img.getHeight();
    }

    private int WrapHorizontalIndex(int i) {
        // This takes care of negative and positive indices beyond the image resolution
        return (i + img.getWidth()) % img.getWidth();
    }

    private Color ApplyKernel(float[][] kernel, int row, int column) {
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;

        int minIndexH = -(kernel.length / 2);
        int maxIndexH = minIndexH + kernel.length;
        int minIndexV = -(kernel[0].length / 2);
        int maxIndexV = minIndexV + kernel[0].length;

        for (int i = minIndexH; i < maxIndexH; ++i) {
            for (int j = minIndexV; j < maxIndexV; ++j) {
                int indexH = WrapHorizontalIndex(row + i);
                int indexV = WrapVerticalIndex(column + j);
                Color col = new Color(img.getRGB(indexH, indexV));

                red += col.getRed() * kernel[i - minIndexH][j - minIndexV];

                green += col.getGreen() * kernel[i - minIndexH][j - minIndexV];

                blue += col.getBlue() * kernel[i - minIndexH][j - minIndexV];

            }
        }

        red = Math.min(Math.max(red, 0.0f), 255.0f);
        green = Math.min(Math.max(green, 0.0f), 255.0f);
        blue = Math.min(Math.max(blue, 0.0f), 255.0f);

        return new Color((int) red, (int) green, (int) blue);
    }

    public void ApplyConvolutionFilter(float[][] kernel) {

       
        BufferedImage outputImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        
       for (int j = 0; j < img.getHeight(); ++j) {
            for (int i = 0; i < img.getWidth(); ++i) {
                // Calculate the gaussian blur
                Color pixelColor = ApplyKernel(kernel, i, j);
                outputImage.setRGB(i, j, pixelColor.getRGB());

            }
        }

        for (int j = 0; j < img.getHeight(); ++j) {
            for (int i = 0; i < img.getWidth(); ++i) {

                int rgb = outputImage.getRGB(i, j);

                img.setRGB(i, j, rgb);
            }
        }
        pictureFrame.repaint();
        wasModified = true;
    }

    private void convertToGreyScale() {

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                int rgb = img.getRGB(x, y); //always returns TYPE_INT_ARGB
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb >> 0) & 0xFF;
                int avg = (red + green + blue) / 3;

                rgb = alpha << 24 | avg << 16 | avg << 8 | avg << 0;

                img.setRGB(x, y, rgb);

            }
        }
        pictureFrame.repaint();
        wasModified = true;

    }

    private void changeGamma(int value) {

        BufferedImage outputImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        try {

            float val = (float) value * -0.01f;


            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgb;

                    float[] hsb = Color.RGBtoHSB(colorMatrix[y][x].getRed(), colorMatrix[y][x].getGreen(), colorMatrix[y][x].getBlue(), null);

                    float[] hsbOrig = Color.RGBtoHSB(colorMatrix[y][x].getRed(), colorMatrix[y][x].getGreen(), colorMatrix[y][x].getBlue(), null);

                    hsb[2] = hsbOrig[2] + val;

                    if (hsb[2] > 1) {
                        hsb[2] = 0.99999999999999f;
                    } else if (hsb[2] <= 0) {
                        hsb[2] = 0.00001f;
                    }

                    rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    outputImage.setRGB(x, y, rgb);
                }
            }

            for (int j = 0; j < img.getHeight(); ++j) {
                for (int i = 0; i < img.getWidth(); ++i) {

                    int rgb = outputImage.getRGB(i, j);

                    img.setRGB(i, j, rgb);
                }
            }
            pictureFrame.repaint();
            wasModified = true;
        } catch (Exception e) {
            messageArea.setText("Could not Execute Current Operation");
        }

    }

    private void enableAll() {
        bwSlider.setEnabled(true);
        brightSlider.setEnabled(true);
        saturationSlider.setEnabled(true);
        sharpSlider.setEnabled(true);
        rtSlider.setEnabled(true);
        gtSlider.setEnabled(true);
        btSlider.setEnabled(true);
        gammaCorrectionSlider.setEnabled(true);
        bFilterCheckBox.setEnabled(true);
        gaussianFilterCheckBox.setEnabled(true);
        edfCheckBox.setEnabled(true);
        greyscCheckBox.setEnabled(true);
        applyButton.setEnabled(true);
        resetButton.setEnabled(true);
        messageArea.setEditable(true);
        viewButton.setEnabled(true);
    }

    private void disableAll() {
        bwSlider.setEnabled(false);
        brightSlider.setEnabled(false);
        saturationSlider.setEnabled(false);
        sharpSlider.setEnabled(false);
        rtSlider.setEnabled(false);
        gtSlider.setEnabled(false);
        btSlider.setEnabled(false);
        gammaCorrectionSlider.setEnabled(false);
        bFilterCheckBox.setEnabled(false);
        gaussianFilterCheckBox.setEnabled(false);
        edfCheckBox.setEnabled(false);
        greyscCheckBox.setEnabled(false);
        applyButton.setEnabled(false);
        resetButton.setEnabled(false);
        messageArea.setEditable(false);
        viewButton.setEnabled(false);
    }


}
