package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp
public class TeleOP extends OpMode{
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor, launch_motor, intakeMotor;
    double y, x, rx;
    Drivetrain main_train;
    Launcher launcher;
    VoltageSensor voltageSensor;
    public boolean trigger_is_pressed(float trigger){
        return trigger >= 5.0f;
    }
    @Override
    public void init(){
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake_motor");
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        launch_motor = hardwareMap.get(DcMotorEx.class, "launcher_motor");
        main_train = new Drivetrain(hardwareMap);
        main_train.init();
        launcher = new Launcher(launch_motor, voltageSensor);
    }
    @Override
    public void loop(){
        main_train.setVectorPower(gamepad1.left_stick_x,-gamepad1.left_stick_y,gamepad1.right_stick_x);
        launcher.update_velocity((int) gamepad1.right_trigger * 300 + 1500);
        if(gamepad1.right_bumper){intakeMotor.setPower(0.7);}
        else if (gamepad1.left_bumper){intakeMotor.setPower(-0.7);}
        else {intakeMotor.setPower(0);}
    }
}