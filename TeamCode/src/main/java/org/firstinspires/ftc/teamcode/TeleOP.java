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
    private Servo clawL;
    private Servo clawR;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelBR");
        clawL = hardwareMap.get(Servo.class, "clawL");
        clawR = hardwareMap.get(Servo.class, "clawR");
	
	motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
	telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        double startPositionL = 0;
        double startPositionR = 0;
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            double powerX = this.gamepad1.left_stick_x;
            double powerY = this.gamepad1.left_stick_y;
            double leftpower = (powerY-powerX)/2;
            double rightpower = (powerY+powerX)/2;
            motorWheelFL.setPower(leftpower);
            motorWheelFR.setPower(rightpower);
            motorWheelBL.setPower(leftpower);
            motorWheelBR.setPower(rightpower);
            telemetry.addData("Left Power", leftpower);
            telemetry.addData("Right Power", rightpower);
            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
            telemetry.addData("BR Motor Power", motorWheelBR.getPower());
            telemetry.update();
            float left_trigger = this.gamepad1.left_trigger;
            float right_trigger = this.gamepad1.right_trigger;
            float movement = 0;

            movement += left_trigger+0.1 - right_trigger+0.1;
            if((clawL.getPosition() - movement) >= 0.0 && (clawL.getPosition() - movement) <= startPositionL) clawL.setPosition(clawL.getPosition() - movement);
            if((clawR.getPosition() + movement) <= 1.0 && (clawL.getPosition() + movement) <= startPositionR) clawR.setPosition(clawR.getPosition() + movement);

            telemetry.addData("Left_trigger", this.gamepad1.left_trigger);
            telemetry.addData("Right_trigger", this.gamepad1.right_trigger);
            telemetry.update();
        }
    }
}
