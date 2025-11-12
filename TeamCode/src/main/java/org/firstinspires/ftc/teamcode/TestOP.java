package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
@TeleOp
public class TestOP extends OpMode {
    ElapsedTime et = new ElapsedTime();
    Launcher launcher;
    DcMotorEx main_motor;
    static int targetVelo =2000;
    @Override
    public void init() {
        main_motor = hardwareMap.get(DcMotorEx.class, "lM");
        launcher = new Launcher(main_motor);
    }

    @Override
    public void loop() {
        if (et.seconds()<10){
            launcher.update_velocity(targetVelo);
        } else if (et.seconds()<20){
            launcher.update_velocity(1500);
        }else if (et.seconds()>20){
            et.reset();
        }
        TelemetryPacket tp = new TelemetryPacket();
        tp.put("target", launcher.target_velocity);
        tp.put("realVel", main_motor.getVelocity());
        tp.put("pwr", main_motor.getPower());
        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.sendTelemetryPacket(tp);
    }
}
