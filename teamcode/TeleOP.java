package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp
public class TeleOP extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
    private CRServo motorCollector;
    private DcMotor motorExtenderRight;
    private DcMotor motorExtenderLeft;
    private DcMotor motorDeposit;
    private DcMotor motorLift;
    private CRServo motorRotator;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
        motorCollector = hardwareMap.get(CRServo.class, "motorCollector");
        motorExtenderRight = hardwareMap.get(DcMotor.class, "motorExtenderRight");
        motorExtenderLeft = hardwareMap.get(DcMotor.class, "motorExtenderLeft");
        motorDeposit = hardwareMap.get(DcMotor.class, "motorDeposit");
        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
        motorRotator = hardwareMap.get(CRServo.class, "motorRotator");
	
	    motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorCollector.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorCollector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorExtenderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorExtenderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorExtenderLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorExtenderLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorDeposit.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorDeposit.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        
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
            float armExtend = this.gamepad2.right_stick_y;
            boolean liftUp = this.gamepad2.dpad_up;
            boolean liftDown = this.gamepad2.dpad_down;
            boolean collector = this.gamepad2.right_bumper;
            boolean reverse = this.gamepad2.left_bumper;


            motorExtenderRight.setPower(-extend);
            motorExtenderLeft.setPower(extend);
            if(motorExtenderRight.getCurrentPosition() > motorExtenderLeft.getCurrentPosition()){
                motorExtenderRight.setPower((-extend)* (motorExtenderLeft.getCurrentPosition()/motorExtenderRight.getCurrentPosition()));
            }
            else if(motorExtenderRight.getCurrentPosition() < motorExtenderLeft.getCurrentPosition()){
                motorExtenderLeft.setPower((extend)* (motorExtenderRight.getCurrentPosition()/motorExtenderLeft.getCurrentPosition()));
            }
            motorDeposit.setPower(armExtend);

            motorRotator.setPower(right_trigger/2-left_trigger/2);
            if(liftUp){
                motorLift.setPower(0.5);
            }
            else{
                motorLift.setPower(0);
            }
            if(liftDown){
                motorLift.setPower(-0.5);
            }
            else{
                motorLift.setPower(0);
            }
            if(collector){
                motorCollector.setPower(-0.5);
            }
            else{
                motorCollector.setPower(0);
            }
            if(reverse){
                motorCollector.setPower(0.5);
            }
            else{
                motorCollector.setPower(0);
            }
            telemetry.addData("Left_trigger", this.gamepad2.left_trigger);
            telemetry.addData("Right_trigger", this.gamepad2.right_trigger);
            telemetry.update();
        }
    }
}
