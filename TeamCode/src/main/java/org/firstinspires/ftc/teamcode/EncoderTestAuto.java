package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="EncoderAuto", group="auto")
public class EncoderTestAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(this);

        bot.init(hardwareMap);

        waitForStart();

        //45 pivot 10 inch extend
        bot.setArmPos(640);
        bot.setArmPos(500);
        bot.setExtendPos(10.0); //inches alright

        bot.retractArm();
        bot.setArmPos(100);

        sleep(1000);

        //45 pivot 10 extend w/ tick
        bot.setArmPos(bot.degreeToTick(45.0));
        bot.setExtendPos(bot.inchToTick(10.0));

        //drive strafe pivot
        bot.encoderDrive(1.0, 10);
        bot.encoderStrafe(1.0, 10);
        bot.encoderTurn(1.0, 180);

    }
}