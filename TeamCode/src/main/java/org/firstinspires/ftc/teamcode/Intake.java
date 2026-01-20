package org.firstinspires.ftc.teamcode;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Intake {
    DcMotorEx main_motor;
    VoltageSensor voltageSensor;
    public static double Kp = 0.03f, Ki = 0.00000000000000001f, Kd = 0.0f;
    public static int target_pos;
    public static int stepSize = 15+
            ;
    public int current_pos;
    public double pwr;
    PIDCoefficients coefficients = new PIDCoefficients(Kp, Ki, Kd);
    BasicPID pid = new BasicPID(coefficients);
    ElapsedTime et = new ElapsedTime();
    Intake(HardwareMap hw){
        main_motor=hw.get(DcMotorEx.class, "intake_motor");
        main_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        voltageSensor = hw.get(VoltageSensor.class, "Control Hub");
        current_pos = 0;
    }
    void setTarget_pos(int v){
        target_pos = v;
    }
    void update_pos(){
        current_pos=main_motor.getCurrentPosition();
        pwr = pid.calculate(target_pos,
                current_pos
        ) ;
        main_motor.setPower(pwr);
    }

    void intakeStep(boolean dir){
        if (dir){
            setTarget_pos(current_pos+stepSize);
        } else {
            setTarget_pos(current_pos-stepSize);
        }
    }
    void start(){main_motor.setPower(.5f);}
}
