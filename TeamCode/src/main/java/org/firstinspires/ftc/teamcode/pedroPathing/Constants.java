package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static double
            forward_TTI = 0.0f,
            strafe_TTI = 0.0f,
            turn_TTI = 0.0f;
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(8);

/*    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1.0f)
            .rightFrontMotorName("rf_motor")
            .rightRearMotorName("rb_motor")
            .leftFrontMotorName("lf_motor")
            .leftRearMotorName("lb_motor")
            .leftFrontMotorDirection(DcMotorEx.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorEx.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorEx.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorEx.Direction.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(
            0.99,
            100,
            1,
            1
    );
*/
    public static DriveEncoderConstants localizerConstants = new DriveEncoderConstants()
            .rightFrontMotorName("mEX_rightFront")
            .rightRearMotorName("mEX_rightBack")
            .leftFrontMotorName("mEX_leftFront")
            .leftRearMotorName("mEX_leftBack")
            .rightFrontEncoderDirection(Encoder.FORWARD)
            .rightRearEncoderDirection(Encoder.FORWARD)
            .leftFrontEncoderDirection(Encoder.FORWARD)
            .leftRearEncoderDirection(Encoder.FORWARD)
            .forwardTicksToInches(forward_TTI)
            .strafeTicksToInches(strafe_TTI)
            .turnTicksToInches(turn_TTI);


    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .driveEncoderLocalizer(localizerConstants)
                .build();
    }
}
