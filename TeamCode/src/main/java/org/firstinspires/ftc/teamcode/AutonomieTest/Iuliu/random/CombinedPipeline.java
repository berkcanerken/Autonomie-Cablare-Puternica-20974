package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.random;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;

import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class CombinedPipeline extends OpenCvPipeline
{
 private Mat hsvImage = new Mat();
 private Mat mask = new Mat();
 private Mat hierarchy = new Mat();
 private List<MatOfPoint> contours = new ArrayList<>();
 private Point squareCenter = new Point();

 public Scalar nonSelectedColor = new Scalar(255, 0, 0);
 public Scalar selectedColor = new Scalar(0, 0, 255);
 public Rect rect1 = new Rect(8, 8, 30, 30);
 public Rect rect2 = new Rect(140, 0, 25, 25);
 public Rect rect3 = new Rect(270, 14, 30, 30);
 public Scalar lowerBlue = new Scalar(106, 100, 50);
 public Scalar upperBlue = new Scalar(230, 255, 255);
 private int selectedRect = -1;

 @Override
 public Mat processFrame(Mat input)
 {

  Imgproc.cvtColor(input, hsvImage, Imgproc.COLOR_RGB2HSV);

  Core.inRange(hsvImage, lowerBlue, upperBlue, mask);

  contours.clear();
  Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

  Mat processedFrame = input.clone();
  Imgproc.drawContours(processedFrame, contours, -1, new Scalar(0, 255, 0), 2);

  // Calculate the position of the detected square
  calculateSquarePosition();

  // Draw coordinates on the processed frame
  if (!contours.isEmpty())
  {
   String coordinates = "X: " + String.format("%.2f", squareCenter.x) + " Y: " + String.format("%.2f", squareCenter.y);
   Imgproc.putText(
           processedFrame,
           coordinates,
           new Point(10, 130),
           Imgproc.FONT_HERSHEY_SIMPLEX,
           0.5,
           new Scalar(0, 0, 255),
           1
   );
  }

  drawRectangles(processedFrame);

  return processedFrame;
 }

 private void drawRectangles(Mat input)
 {
  Imgproc.rectangle(input, rect1, nonSelectedColor);
  Imgproc.rectangle(input, rect2, nonSelectedColor);
  Imgproc.rectangle(input, rect3, nonSelectedColor);

  switch (selectedRect)
  {
   case 1:
    Imgproc.rectangle(input, rect1, selectedColor);
    break;
   case 2:
    Imgproc.rectangle(input, rect2, selectedColor);
    break;
   case 3:
    Imgproc.rectangle(input, rect3, selectedColor);
    break;
  }
 }

 private void calculateSquarePosition()
 {
  squareCenter.x = 0;
  squareCenter.y = 0;

  if (!contours.isEmpty())
  {
   double maxArea = -1;
   int maxAreaIdx = -1;
   for (int i = 0; i < contours.size(); i++)
   {
    double area = Imgproc.contourArea(contours.get(i));
    if (area > maxArea)
    {
     maxArea = area;
     maxAreaIdx = i;
    }
   }

   if (maxAreaIdx != -1)
   {
    // Calculate the center of the largest contour
    Moments moments = Imgproc.moments(contours.get(maxAreaIdx));
    squareCenter.x = moments.m10 / moments.m00;
    squareCenter.y = moments.m01 / moments.m00;
   }
  }
 }
}
