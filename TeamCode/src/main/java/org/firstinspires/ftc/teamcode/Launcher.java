package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.ftccommon.configuration.EditLynxModuleActivity;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Launcher{
    DcMotorEx main_motor;
    VoltageSensor voltageSensor;
    public static double Kp = 0.0003832f, Ki = 0.00000000000000001f, Kd = 0.0f, Kf = 0.00035f;
    public static int target_velocity;
    public static int max_error = 2000;
    public static double burnout_timer = 1;
    PIDCoefficients coefficients = new PIDCoefficients(Kp, Ki, Kd);
    BasicPID pid = new BasicPID(coefficients);
    ElapsedTime et = new ElapsedTime();
    Launcher(DcMotorEx m, VoltageSensor vs){
        main_motor = m;
        main_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        voltageSensor = vs;
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
        double pwr = pid.calculate(target,
                main_motor.getVelocity()
        ) + (Kf * main_motor.getVelocity()
                * (14.0 / voltageSensor.getVoltage())
            );
        setTarget_velocity(target);
        if(main_motor.getVelocity()>50) {
            et.reset();
        }
        if(et.seconds()>burnout_timer){
            pwr = 0;
        }
        main_motor.setPower(pwr);
    }
    void start(){main_motor.setPower(.5f);}
}