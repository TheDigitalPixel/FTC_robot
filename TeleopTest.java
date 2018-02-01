package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.MotorConfiguration;

@TeleOp
public class TeleopTest extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
  //  private DcMotor clawLift;
    private Servo clawL;
    private Servo clawR;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
       // clawLift = hardwareMap.get(DcMotor.class, "clawLift");
        clawL = hardwareMap.get(Servo.class, "clawL");
        clawR = hardwareMap.get(Servo.class, "clawR");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)

        double startPositionL = clawL.getPosition();
        double startPositionR = clawR.getPosition();

        while (opModeIsActive()) {
//            telemetry.addData("Status", "Running");
//
//            double powerX = this.gamepad1.left_stick_x;
//            double powerY = this.gamepad1.left_stick_y;
//            double leftpower = (powerY-powerX)/2;
//            double rightpower = (powerY+powerX)/2;
//            motorWheelFL.setPower(leftpower);
//            motorWheelFR.setPower(rightpower);
//            motorWheelBL.setPower(leftpower);
//            motorWheelBR.setPower(rightpower);
//            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
//            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
//            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
//            telemetry.addData("BR Motor Power", motorWheelBR.getPower());
//
//            double movement = 0;
//            float left_trigger = this.gamepad1.left_trigger;
//            float right_trigger = this.gamepad1.right_trigger;
//
//            movement += left_trigger*0.1 - right_trigger*0.1;
//            if((clawL.getPosition() - movement) >= 0.0 && (clawL.getPosition() - movement) <= startPositionL) clawL.setPosition(clawL.getPosition() - movement);
//            if((clawR.getPosition() + movement) <= 1.0 && (clawL.getPosition() + movement) <= startPositionR) clawR.setPosition(clawR.getPosition() + movement);
//
//            telemetry.addData("Left_trigger", this.gamepad1.left_trigger);
//            telemetry.update();
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

            boolean uplift = gamepad1.dpad_up;
            boolean downlift = gamepad1.dpad_down;

           // if (uplift == true) clawLift.setPower(20);
           // if (downlift == true) clawLift.setPower(-20);

            double rt = gamepad1.right_trigger;
            double lt = gamepad1.left_trigger;

            double currentLeft = clawL.getPosition();
            double currentRight = clawR.getPosition();

            if (rt != 0) {
                clawL.setPosition(-1);
                clawR.setPosition(1);
            }


//            while (rt != 0 && currentLeft < 1 && currentLeft > 0 && currentRight < 1 && currentRight > 0) {
//                currentLeft = clawL.getPosition();
//                currentRight = clawR.getPosition();
//                clawR.setPosition(currentRight + 0.01);
//                clawL.setPosition(currentLeft + 0.01);
           // }
//            while (lt != 0 && currentLeft < 1 && currentLeft > -1 && currentRight < 1 && currentRight > -1) {
//                currentLeft = clawL.getPosition();
//                currentRight = clawR.getPosition();
//                clawR.setPosition(currentRight - 0.01);
//                clawL.setPosition(currentLeft - 0.01);
           // }
        }
    }
}
