package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.Opmodes;



import  org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive;
import static  org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "nou")

public class Autonomie extends LinearOpMode {

    int Blue = 255;
    DcMotor motor1;
    @Override
    public void runOpMode() throws InterruptedException {

            SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

            waitForStart();

            while(opModeIsActive()){

                if(sensor.blue() > Blue)
                    servo.setPosition(1);
                else
                    servo.setPosition(0);

                telemetry.addData("blue", sensor.blue());
                telemetry.update();
            }

            if(isStopRequested())
                return;
    }

}
