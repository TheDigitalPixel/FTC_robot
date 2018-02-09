package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Drive extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
    private DcMotor clawL;
    private Servo clawR;
    private DcMotor linSlide;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
        linSlide = hardwareMap.get(DcMotor.class, "linSlide");
        clawL = hardwareMap.get(DcMotor.class, "clawL");
        clawR = hardwareMap.get(Servo.class, "clawR");

        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)

        double startPositionL = 0;
        double startPositionR = clawR.getPosition();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            /*
	    double powerX = this.gamepad1.left_stick_x;
            double powerY = this.gamepad1.left_stick_y;
            double leftpower = (powerY-powerX)/2;
            double rightpower = (powerY+powerX)/2;
            motorWheelFL.setPower(leftpower);
            motorWheelFR.setPower(rightpower);
            motorWheelBL.setPower(leftpower);
            motorWheelBR.setPower(rightpower);
	    */
	    double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            
	    final double v1 = r * Math.cos(robotAngle) - rightX;
            final double v2 = -r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) - rightX;
            final double v4 = -r * Math.cos(robotAngle) - rightX;

            motorWheelBL.setPower(v1);
            motorWheelBR.setPower(v2);
            motorWheelFL.setPower(v3);
            motorWheelFR.setPower(v4);
            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
            telemetry.addData("BR Motor Power", motorWheelBR.getPower());

            double movement = 0;
            boolean left_trigger = this.gamepad1.dpad_left;
            boolean right_trigger = this.gamepad1.dpad_right;
            
	    if(this.gamepad1.a) {
                linSlide.setPower(0.25);
	    } else if(this.gamepad1.b) {
                linSlide.setPower(-0.25);                
	    }

            movement += ((left_trigger)? 1:0) + ((right_trigger)? -1: 0);
            clawL.setPower(0 - movement);
            clawR.setPower(movement);
            

            telemetry.addData("Left_trigger", this.gamepad1.left_trigger);
            telemetry.update();
        }
    }
}
