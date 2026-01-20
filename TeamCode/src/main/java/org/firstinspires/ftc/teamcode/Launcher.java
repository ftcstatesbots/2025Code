package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.ftccommon.configuration.EditLynxModuleActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Launcher{
    DcMotorEx main_motor;
    VoltageSensor voltageSensor;
    public double current_velocity;
    public static double Kp = 0.001f, Ki = 0.00000000000000001f, Kd = 0.0f, Kf = 0.0005f;
    public static int target_velocity;
    public static int max_error = 2000;
    public static double burnout_timer = 3;
    PIDCoefficients coefficients = new PIDCoefficients(Kp, Ki, Kd);
    BasicPID pid = new BasicPID(coefficients);
    ElapsedTime et = new ElapsedTime();
    Launcher(HardwareMap hw){
        main_motor=hw.get(DcMotorEx.class, "launcher_motor");
        main_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        voltageSensor = hw.get(VoltageSensor.class, "Control Hub");
        main_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        burnout_timer = 2;
    }
//  double update(double target){
//      return controller.calculate(target, main_motor.getVelocity());
//  }
//  void set_velocity(){
//      main_motor.setPower(update(target_velocity));
//  }
    void setTarget_velocity(int v){
        target_velocity = v;
    }
    void update_velocity(int target){
        current_velocity = main_motor.getVelocity();
        double pwr = pid.calculate(target,
                current_velocity
        ) + (Kf * current_velocity
                * (14.0 / voltageSensor.getVoltage())
            );
        setTarget_velocity(target);
        if(current_velocity>20) {
            et.reset();
        }
        if(et.seconds()>burnout_timer){
            pwr = 0;
        }
        main_motor.setPower(pwr);
    }
    public void end(){
        main_motor.setPower(0);
    }
}