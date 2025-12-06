package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOP extends OpMode {
    DcMotorEx launch_motor, intake_motor,
            right_back_motor, right_front_motor,
            left_back_motor, left_front_motor;

    @Override
    public void init(){
        // Getting motors
        launch_motor = hardwareMap.get(DcMotorEx.class, "mEX_launch");
        intake_motor = hardwareMap.get(DcMotorEx.class, "mEX_intake");
        right_back_motor = hardwareMap.get(DcMotorEx.class, "mEX_rightBack");
        right_front_motor = hardwareMap.get(DcMotorEx.class, "mEX_rightFront");
        left_back_motor = hardwareMap.get(DcMotorEx.class, "mEX_leftBack");
        left_front_motor = hardwareMap.get(DcMotorEx.class, "mEX_leftFront");

        // Setting motor directions
        intake_motor.setDirection(DcMotor.Direction.REVERSE);
        left_back_motor.setDirection(DcMotor.Direction.REVERSE);
        left_front_motor.setDirection(DcMotor.Direction.REVERSE);

        // Zero power behaviors
        right_back_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_front_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    @Override
    public void loop(){

    }
}
