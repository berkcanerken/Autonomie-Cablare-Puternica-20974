package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.Opmodes;

import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes.BlueConeDetector;
import org.firstinspires.ftc.teamcode.software.OpenCV.initCamera;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "ColorDetectionAutonomous", group = "Iuliu")
public class BlueConeDetectorClass extends LinearOpMode {

    ElapsedTime timer;

    OpenCvCamera externalCamera;
     public static BlueConeDetector pipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the hardwareMap


        // Create an instance of initCamera
        initCamera camera = new initCamera();

        // Call the initExternalCamera method on the camera instance
        camera.initExternalCamera();

        // Initialize the camera and pipeline
        externalCamera = camera.externalCamera;
        pipeline = new BlueConeDetector();

        waitForStart();

        while (opModeIsActive()) {
            if (pipeline.isBlueDetected()) {
                // Assuming you have a servo object defined elsewhere in your code
                servo.setPosition(1);
            } else {
                servo.setPosition(0);
            }
            Point squareCenter = pipeline.getSquareCenter(); // Get the center coordinates

            telemetry.addData("Square Center X", squareCenter.x); // Display X coordinate
            telemetry.addData("Square Center Y", squareCenter.y); // Display Y coordinate
            telemetry.addData("Servo Position", servo.getPosition());
            telemetry.update();
        }
    }
}
