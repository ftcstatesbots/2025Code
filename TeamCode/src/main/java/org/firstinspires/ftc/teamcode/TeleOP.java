package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOP extends OpMode {
    DcMotorEx[] train_motors;
    DcMotorEx launch_motor, intake_motor,
            right_back_motor, right_front_motor,
            left_back_motor, left_front_motor;
    Drivetrain main_train;
    Launcher launcher;

    @Override
    public void init(){
        // Getting motors
        launch_motor = hardwareMap.get(DcMotorEx.class, "mEX_launch");
        intake_motor = hardwareMap.get(DcMotorEx.class, "mEX_intake");
        train_motors = new DcMotorEx[]{
            hardwareMap.get(DcMotorEx.class, "mEX_rightBack"),
            hardwareMap.get(DcMotorEx.class, "mEX_rightFront"),
            hardwareMap.get(DcMotorEx.class, "mEX_leftBack"),
            hardwareMap.get(DcMotorEx.class, "mEX_leftFront")
        };

        // Setting motor directions
        intake_motor.setDirection(DcMotor.Direction.REVERSE);
        left_back_motor.setDirection(DcMotor.Direction.REVERSE);
        left_front_motor.setDirection(DcMotor.Direction.REVERSE);

        // Zero power behaviors
        right_back_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_front_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        main_train = new Drivetrain(train_motors);
    }
    @Override
    public void loop(){

    }
}
