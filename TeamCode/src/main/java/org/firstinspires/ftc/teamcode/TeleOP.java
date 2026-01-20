package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp
public class TeleOP extends OpMode{
    Drivetrain main_train;
    Launcher launcher;
    Intake intake;

    public boolean trigger_is_pressed(float trigger){
        return trigger >= 5.0f;
    }
    @Override
    public void init(){
        main_train = new Drivetrain(hardwareMap);
        main_train.init();
        launcher = new Launcher(hardwareMap);
        intake = new Intake(hardwareMap);
    }
    @Override
    public void loop(){
        main_train.setVectorPower(-gamepad1.left_stick_x,-gamepad1.left_stick_y,gamepad1.right_stick_x);
        launcher.update_velocity((int) gamepad1.right_trigger * 1500 + 1500);
        if(gamepad1.right_bumper){
            intake.intakeStep(true);
        } else if (gamepad1.left_bumper){
            intake.intakeStep(false);
        }
        if(gamepad1.optionsWasPressed()) Launcher.burnout_timer=-1;
        if(gamepad1.shareWasPressed()) main_train.resetHeading();

        intake.update_pos();
        telemetry.addData("Lvelo", launcher.current_velocity);
        if(launcher.current_velocity>1450)telemetry.addLine("Ready");
        else telemetry.addLine("Slow");
        telemetry.update();

        TelemetryPacket tp = new TelemetryPacket();
        tp.put("targetLVal", Launcher.target_velocity);
        tp.put("inPos", intake.current_pos);
        tp.put("targetInPos", Intake.target_pos);
        tp.put("InPWR", intake.pwr);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.sendTelemetryPacket(tp);
    }

    @Override
    public void stop() {
        launcher.end();
    }
}