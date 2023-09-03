package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.Opmodes;

import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes.BlueConeDetector;
import org.firstinspires.ftc.teamcode.software.OpenCV.initCamera;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "ColorDetectionAutonomous", group = "Iuliu")
public class BlueConeDetectorClass extends LinearOpMode {


    OpenCvCamera externalCamera;
    BlueConeDetector pipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialization of hardwareMap is handled by FTC framework

        // Create an instance of initCamera and pass hardwareMap to the constructor
        initCamera camera = new initCamera(hardwareMap);

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
