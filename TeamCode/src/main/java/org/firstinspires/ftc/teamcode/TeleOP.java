package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp
public class TeleOP extends OpMode{
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor, launcher_motor, intakeMotor;
    double y, x, rx;
    Drivetrain main_train;
    Launcher launcher;
    VoltageSensor voltageSensor;
    public boolean trigger_is_pressed(float trigger){
        return trigger >= 5.0f;
    }
    @Override
    public void init(){
        DcMotorEx[] motors = {
            rb_motor = hardwareMap.get(DcMotorEx.class, "right_back_motor"),
            rf_motor = hardwareMap.get(DcMotorEx.class, "right_front_motor"),
            lb_motor = hardwareMap.get(DcMotorEx.class, "left_back_motor"),
            lf_motor = hardwareMap.get(DcMotorEx.class, "left_front_motor")
        };
        intakeMotor = hardwareMap.get(DcMotorEx.class, "iM");
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        launcher_motor = hardwareMap.get(DcMotorEx.class, "lM");
        main_train = new Drivetrain(motors);
        launcher = new Launcher(launcher_motor, voltageSensor);
    }
    @Override
    public void loop(){
        main_train.update_power(gamepad1, true);
        launcher.update_velocity((int) gamepad1.right_trigger * 300 + 1700);
        if(gamepad1.a){
            intakeMotor.setPower(0.7);
        } else if (gamepad1.b) {
            intakeMotor.setPower(-0.7);
        } else {
            intakeMotor.setPower(0);
        }
    }
}