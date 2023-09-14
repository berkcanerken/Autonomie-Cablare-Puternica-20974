package org.firstinspires.ftc.teamcode.AutonomieTest.Mara;

import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.*;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servol;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive;

@TeleOp(name="TeleOpMara")
public class TeleOp1 extends LinearOpMode {

    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new SampleMecanumDrive(hardwareMap);
        backLeftMotor= (DcMotor) hardwareMap.get(constants.class,"motorLB");
        frontLeftMotor= (DcMotor) hardwareMap.get(constants.class,"motorLF");
        backRightMotor= (DcMotor) hardwareMap.get(constants.class,"motorRB");
        frontLeftMotor= (DcMotor) hardwareMap.get(constants.class,"motorRF");

        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        //ServoDS, ServoDD, ServoIS, ServoID, PowerX, PowerY
        while(opModeIsActive())
        {
            //gamepad1 sa foloseasca miscarea bratului si a servourilor
            //gamepad2 sa foloseasca miscarea robotului
            if(gamepad1.a)
            {
                servol.setPosition(ServoDS);
                servor.setPosition(ServoDD);
            }
            if(gamepad1.b)
            {
                servol.setPosition(ServoIS);
                servor.setPosition(ServoID);
            }
            if(gamepad2.dpad_up)
            {
                y=0.5;
            }
            if(gamepad2.dpad_down)
            {
                y=-0.5;
            }
            if(gamepad2.dpad_right)
            {
                x=-0.5;
            }
            if(gamepad2.dpad_left)
            {
                x=0.5;
            }
        }
        //giani1
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        if(isStopRequested())
        {
            return;
        }
        idle();
    }
}
