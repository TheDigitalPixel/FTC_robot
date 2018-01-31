package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class FirstOPMainClass extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
    private Servo clawL;
    private Servo clawR;

    @Override
    public void runOpMode() throws InterruptedException {



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
            telemetry.addData("Status", "Running");
            double powerX = this.gamepad1.left_stick_x;
            double powerY = this.gamepad1.left_stick_y;
            double leftpower = powerY-powerX;
            double rightpower = powerY+powerX;
            motorWheelFL.setPower(leftpower);
            motorWheelFR.setPower(rightpower);
            motorWheelBL.setPower(leftpower);
            motorWheelBR.setPower(rightpower);
            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
            telemetry.addData("BR Motor Power", motorWheelBR.getPower());

            double movement = 0;
            boolean left_trigger = this.gamepad1.dpad_left;
            boolean right_trigger = this.gamepad1.dpad_right;

            movement += ((left_trigger)? 1:0) + ((right_trigger)? -1: 0);
            if((clawL.getPosition() - movement) >= 0.0 && (clawL.getPosition() - movement) <= startPositionL) clawL.setPosition(clawL.getPosition() - movement);
            if((clawR.getPosition() + movement) <= 1.0 && (clawL.getPosition() + movement) <= startPositionR) clawR.setPosition(clawR.getPosition() + movement);

            telemetry.addData("Left_trigger", this.gamepad1.left_trigger);
            telemetry.update();
        }
    }
}
