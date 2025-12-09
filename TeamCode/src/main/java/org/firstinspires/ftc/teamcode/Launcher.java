package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Launcher {
    DcMotorEx main_motor;
    boolean cruise_control;
    byte speed;
    Launcher(DcMotorEx m){main_motor = m;}
    public void toggle_cruise_control(){cruise_control = !cruise_control;}
    public void whats_next(Gamepad pad){
        if (pad.aWasPressed()) toggle_cruise_control();
        if (cruise_control){
             if (pad.leftBumperWasPressed() && speed > 0) speed--;
             else if (pad.rightBumperWasPressed() && speed < Byte.MAX_VALUE) speed++;
             else {

             }
        }
        else main_motor.setPower(pad.right_trigger);
    }
}
