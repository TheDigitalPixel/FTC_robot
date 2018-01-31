package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class AutonomousBlue extends LinearOpMode{

    private ColorSensor sensor;
    private Servo arm;

    @Override
    public void runOpMode() throws InterruptedException {
        sensor = hardwareMap.colorSensor.get("color");
        arm = hardwareMap.get(Servo.class, "arm");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        int r,b;
        while(opModeIsActive()){
            r = sensor.red();
            b = sensor.blue();

            if(b >= 200){
                arm.setPosition(0.0);
            }else if(r >= 200){
                arm.setPosition(1.0);
            }
        }
    }
}
