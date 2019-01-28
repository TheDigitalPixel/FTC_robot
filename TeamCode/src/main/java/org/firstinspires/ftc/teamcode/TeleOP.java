package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOP extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
    private DcMotor motorCollector;
    private DcMotor motorExtender;
    //private Servo motorRotator;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
        motorCollector = hardwareMap.get(DcMotor.class, "motorCollector");
        motorExtender = hardwareMap.get(DcMotor.class, "motorExtender");
       // motorRotator = hardwareMap.get(Servo.class, "motorRotator");
	
	    motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorCollector.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorCollector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorExtender.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        
	telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        float startPositionL = 0;
        float startPositionR = 0;
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
//            float powerX = this.gamepad1.left_stick_x;
//            float powerY = this.gamepad1.left_stick_y;
//            float turnX = -this.gamepad1.right_stick_x;
//            float leftSide = ((powerY + powerX)/2 + turnX)/2;
//            float rightSide = ((powerY + powerX)/2 - turnX)/2;
//
//            motorWheelFL.setPower(-leftSide);
//            motorWheelFR.setPower(rightSide);
//            motorWheelBL.setPower(leftSide);
//            motorWheelBR.setPower(-rightSide);

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = -r * Math.cos(robotAngle) - rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = -r * Math.sin(robotAngle) - rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            motorWheelBL.setPower(v1);
            motorWheelBR.setPower(v2);
            motorWheelFL.setPower(v3);
            motorWheelFR.setPower(v4);
            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
            telemetry.addData("BR Motor Power", motorWheelBR.getPower());
            telemetry.update();

            float left_trigger = this.gamepad2.left_trigger;
            float right_trigger = this.gamepad2.right_trigger;
            float extend = this.gamepad2.left_stick_y;
           // double rotate = this.gamepad2.right_stick_y;

            motorCollector.setPower(right_trigger-left_trigger);
            motorExtender.setPower(-extend);
           // motorRotator.setPosition(rotate);

            telemetry.addData("Left_trigger", this.gamepad2.left_trigger);
            telemetry.addData("Right_trigger", this.gamepad2.right_trigger);
            telemetry.update();
        }
    }
}
