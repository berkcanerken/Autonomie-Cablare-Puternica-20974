package org.firstinspires.ftc.teamcode.AutonomieTest.Mara;

import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.*;
import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.backLeftPower;
import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.backRightPower;
import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.frontLeftPower;
import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.frontRightPower;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servol;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive;

@TeleOp(name="TeleOpMara",group ="Mara")
public class TeleOp1 extends LinearOpMode {

    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new SampleMecanumDrive(hardwareMap);
        DcMotorEx backLeftMotor = hardwareMap.get(DcMotorEx.class, "motorLB");
        DcMotorEx backRightMotor = hardwareMap.get(DcMotorEx.class, "motorRB");
        DcMotorEx frontLeftMotor = hardwareMap.get(DcMotorEx.class, "motorLF");
        DcMotorEx frontRightMotor = hardwareMap.get(DcMotorEx.class, "motorRF");
        DcMotorEx mbrat = hardwareMap.get(DcMotorEx.class,"mbrat");

        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mbrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double y = -gamepad1.left_stick_y / 5;
        double x = -gamepad1.left_stick_x / 5;

        waitForStart();
        servol.setPosition(ServoDS);
        servor.setPosition(ServoDD);
       // difservo.setPoition(Servopoz...);
        //ServoDS, ServoDD, ServoIS, ServoID, PowerX, PowerY
        while (opModeIsActive()) {
            if (gamepad2.a) {
                //servo 1 
                sleep(1000);
                servol.setPosition(ServoDS);
                servor.setPosition(ServoDD);
            }
            if (gamepad2.b) {
                               //servo 1 
                sleep(1000);
                servol.setPosition(ServoIS);
                servor.setPosition(ServoID);
            }

            if(gamepad2.x && gamepad2.y)
            {
                telemetry.addData("Razvi ","Esti un figurant");
                telemetry.update();
            }
            //giumbusluc23-1

            if (gamepad2.dpad_up) y = 0.5;
            if (gamepad2.dpad_down) y = -0.5;
            if (gamepad2.dpad_right) x = -0.5;
            if (gamepad2.dpad_left) x = 0.5;

            if(gamepad2.right_trigger>0) mbrat.setPower(-gamepad2.right_trigger);
            if(gamepad2.left_trigger>0) mbrat.setPower(gamepad2.left_trigger);

            double rx = gamepad1.right_stick_x * 0.5;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y - x + rx) / denominator;
            double backLeftPower = (y + x + rx) / denominator;
            double frontRightPower = (y + x - rx) / denominator;
            double backRightPower = (y - x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            telemetry.clearAll();
            telemetry.update();

        }

        if (isStopRequested()) {
            return;
        }
        idle();
    }
}
