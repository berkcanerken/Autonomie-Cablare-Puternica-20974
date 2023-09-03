package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;
import java.util.ArrayList;
import java.util.List;

public class BlueConeDetector extends OpenCvPipeline {
    private Mat hsvImage = new Mat();
    private Mat mask = new Mat();
    private Mat hierarchy = new Mat();
    private List<MatOfPoint> contours = new ArrayList<>();

    private Point squareCenter = new Point();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsvImage, Imgproc.COLOR_RGB2HSV);

        Scalar lowerBlue = new Scalar(208, 83, 100);
        Scalar upperBlue = new Scalar(240, 100, 100);//H S V

        Core.inRange(hsvImage, lowerBlue, upperBlue, mask);

        contours.clear();
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        Mat processedFrame = input.clone();
        Imgproc.drawContours(processedFrame, contours, -1, new Scalar(0, 255, 0), 2);

        // Calculate the position of the detected square
        calculateSquarePosition();

        // Draw coordinates on the processed frame
        if (!contours.isEmpty()) {
            String coordinates = "X: " + String.format("%.2f", squareCenter.x) + " Y: " + String.format("%.2f", squareCenter.y);
            Imgproc.putText(
                    processedFrame,
                    coordinates,
                    new Point(10, 30),
                    Imgproc.FONT_HERSHEY_SIMPLEX,
                    0.5,
                    new Scalar(0, 0, 255),
                    1
            );
        }

        return processedFrame;
    }

    public boolean isBlueDetected() {
        // If there are contours present, yellow is detected
        return !contours.isEmpty();
    }

    public Point getSquareCenter() {
        return squareCenter;
    }

    public void calculateSquarePosition() {
        squareCenter.x = 0;
        squareCenter.y = 0;

        if (!contours.isEmpty()) {
            // Find the largest contour (assumed to be the square)
            double maxArea = -1;
            int maxAreaIdx = -1;
            for (int i = 0; i < contours.size(); i++) {
                double area = Imgproc.contourArea(contours.get(i));
                if (area > maxArea) {
                    maxArea = area;
                    maxAreaIdx = i;
                }
            }

            if (maxAreaIdx != -1) {
                // Calculate the center of the largest contour
                Moments moments = Imgproc.moments(contours.get(maxAreaIdx));
                squareCenter.x = moments.m10 / moments.m00;
                squareCenter.y = moments.m01 / moments.m00;
            }
        }
    }
}