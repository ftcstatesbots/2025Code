package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import dev.frozenmilk.mercurial.Mercurial

@Mercurial.Attach
@Launcher.Attach
@TeleOp
class TestOP : OpMode() {
    var et = ElapsedTime()
    override fun init() {
        et.reset()
        Mercurial.gamepad1.a
            .onTrue()
    }

    override fun loop() {
        while (et.seconds()<10)

    }

    //I plan on adding stuff to test motors based on port,
    //and enable dashboard config for all variables
    //and PID tuning for the launcher
}