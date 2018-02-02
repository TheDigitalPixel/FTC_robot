package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.*;

@TeleOp
public class AutonomousRed extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;

    private ColorSensor sensor;
    private Servo arm;

    private final double drivePidKp = 1;     // Tuning variable for PID.
    private final double drivePidTi = 1.0;   // Eliminate integral error in 1 sec.
    private final double drivePidTd = 0.1;   // Account for error in 0.1 sec.
    // Protect against integral windup by limiting integral term.
    private final double drivePidIntMax = 1.0;  // Limit to max speed.
    private final double driveOutMax = 1.0;  // Motor output limited to 100%.


    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
        sensor = hardwareMap.colorSensor.get("color");
        arm = hardwareMap.get(Servo.class, "arm");

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

        int r,b;
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        long elapsedTime = 0;
        boolean foundJewel = false;
        boolean movedJewel = false;
        long con = 0;
        arm.setPosition(0.5);
        while(opModeIsActive()){
            r = sensor.red();
            b = sensor.blue();

            if(!foundJewel && r >= 200){
                endtime = System.currentTimeMillis();
                elapsedtime = endtime - time + con;
                foundJewel = true;
                motorWheelFL.setPower(0.0);
                motorWheelFR.setPower(0.0);
                motorWheelBL.setPower(0.0);
                motorWheelBR.setPower(0.0);
                arm.setPosition(0.0);
                arm.setPosition(0.5);
            }else if(!foundJewel && b >= 200){
                endtime = System.currentTimeMillis();
                elapsedtime = endtime - time + con;
                foundJewel = true;
                motorWheelFL.setPower(0.0);
                motorWheelFR.setPower(0.0);
                motorWheelBL.setPower(0.0);
                motorWheelBR.setPower(0.0);
                arm.setPosition(1.0);
                arm.setPosition(0.5);
            }else if (!foundJewel){
                if (foundJewel) foundJewel = true;
                motorWheelFL.setPower(1.0);
                motorWheelFR.setPower(1.0);
                motorWheelBL.setPower(1.0);
                motorWheelBR.setPower(1.0);
            }else if (System.currentTimeMillis() - endtime < elapsedtime) {
                motorWheelFL.setPower(-1.0);
                motorWheelFR.setPower(-1.0);
                motorWheelBL.setPower(-1.0);
                motorWheelBR.setPower(-1.0);
            }else {
                motorWheelFL.setPower(0.0);
                motorWheelFR.setPower(0.0);
                motorWheelBL.setPower(0.0);
                motorWheelBR.setPower(0.0);
            }
        }
    }
}
