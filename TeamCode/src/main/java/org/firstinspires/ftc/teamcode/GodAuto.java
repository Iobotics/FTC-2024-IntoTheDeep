package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="GodAuto", group="auto")
public class GodAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(this);

        bot.init(hardwareMap);

        waitForStart();

        // # Put sample on Top #
        // forward
        bot.encoderDrive(1.0, 20);
        // strafe left
        bot.encoderStrafe(1.0, 15);
        // pivot arm up
        bot.setArmPos(800); //using tick func
        // extend arm
        bot.setExtendPos(9.75);
        bot.encoderDrive(1,-1);
        // lower pivot arm slightly
        bot.setArmPos(500);
        bot.encoderDrive(1.0, -5);

        bot.setExtendPos(1.0);
        bot.setArmPos(-300);

        // initial strafe for setting up next auto cycle
        bot.encoderStrafe(1.0, 60);

        // # Pickup Cycle and place into high basket
        // loop 3 times
        for(int i = 1; i <= 3; i++) {
            // strafe left
            bot.encoderStrafe(1.0, 5);
            // run intake
            bot.setArmPos(-500);
            // forward
            bot.encoderDrive(1.0, 9);
            bot.setExtendPos(5.0);
            bot.runIntakeForTime(2.0, Bot.INTAKE_FORWARD);
            bot.setExtendPos(1.0);
            bot.setArmPos(0);
            // backward
            bot.encoderDrive(1.0,-18);
            // pivot turn -145
            bot.encoderTurn(1.0, -145);
            // forward closer to basket
            bot.encoderDrive(1.0, 3);
            bot.encoderStrafe(1.0, -8);
            // pivot arm
            bot.setArmPos(1150); //0->2560 tick:degree 0->180
            // extend arm
            bot.setExtendPos(-2785);
            // outtake
            bot.runIntakeForTime(1.0, Bot.INTAKE_BACKWARD);
            // retract arm
            bot.setExtendPos(1.0);
            bot.setArmPos(100);
            // pivot turn 145 and move back
            bot.encoderDrive(1.0, -5);
            bot.encoderTurn(1.0, 145);
            // begin sequence again for the next two blocks
            bot.encoderDrive(1.0, 15);
        }
    }
}