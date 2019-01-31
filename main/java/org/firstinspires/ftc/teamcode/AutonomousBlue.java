package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.*;

@Autonomous
public class AutonomousBlue extends LinearOpMode {

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
    private DcMotor motorExtenderRight;
    private DcMotor motorExtenderLeft;
    private DcMotor motorDeposit;
    private DcMotor motorLift;

    //private ColorSensor sensor;
    //private Servo arm;

//    private final double drivePidKp = 1;     // Tuning variable for PID.
//    private final double drivePidTi = 1.0;   // Eliminate integral error in 1 sec.
//    private final double drivePidTd = 0.1;   // Account for error in 0.1 sec.
//    // Protect against integral windup by limiting integral term.
//    private final double drivePidIntMax = 1.0;  // Limit to max speed.
//    private final double driveOutMax = 1.0;  // Motor output limited to 100%.


    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
        motorExtenderRight = hardwareMap.get(DcMotor.class, "motorExtenderRight");
        motorExtenderLeft = hardwareMap.get(DcMotor.class, "motorExtenderLeft");
        motorDeposit = hardwareMap.get(DcMotor.class, "motorDeposit");
        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
       // sensor = hardwareMap.colorSensor.get("color");
       // arm = hardwareMap.get(Servo.class, "arm");

        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorExtenderLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorExtenderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorDeposit.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorExtenderLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorExtenderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorDeposit.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        motorDeposit.setTargetPosition(motorDeposit.getCurrentPosition()+90);


    }
}
