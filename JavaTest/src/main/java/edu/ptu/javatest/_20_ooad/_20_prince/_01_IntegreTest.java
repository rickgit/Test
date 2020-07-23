package edu.ptu.javatest._20_ooad._20_prince;

import org.junit.Test;

public class _01_IntegreTest {
    @Test
    public void testIntegreNone(){
        new GameUser().handBg(new GameBackgroundOp(),'v');
        new GameUser().handOp(new GameHandOp(),'l');
    }
    public class GameUser{
        public void handOp(GameHandOp gameHandOp,char op){
            switch (op){
                case  'l':
                    gameHandOp.turnLeft();
                    break;
            }
        }
        public void handBg(GameBackgroundOp backgroundOp,char op){
            switch (op){
                case  'v':
                    backgroundOp.openVoice();
                    break;
            }
        }
    }
    interface Game{
         void turnLeft();
         void turnR();
         void turnT();
         void turnB();
         void openVoice();
         void disableVoice();
         void beatyBack();
    }
    public static class GameHandOp implements Game{

        @Override
        public void turnLeft() {

        }

        @Override
        public void turnR() {

        }

        @Override
        public void turnT() {

        }

        @Override
        public void turnB() {

        }

        @Override
        public void openVoice() {

        }

        @Override
        public void disableVoice() {

        }

        @Override
        public void beatyBack() {

        }
    }
    public static class GameBackgroundOp implements Game{

        @Override
        public void turnLeft() {

        }

        @Override
        public void turnR() {

        }

        @Override
        public void turnT() {

        }

        @Override
        public void turnB() {

        }

        @Override
        public void openVoice() {

        }

        @Override
        public void disableVoice() {

        }

        @Override
        public void beatyBack() {

        }
    }

    @Test
    public void testIntegre(){
        new GameUseIntegre().handBg(new IGameIntegreBg() {
            @Override
            public void openVoice() {

            }

            @Override
            public void disableVoice() {

            }

            @Override
            public void beatyBack() {

            }
        }, 'v');
        new GameUseIntegre().handOp(new IGameIntegreHand() {
            @Override
            public void turnLeft() {

            }

            @Override
            public void turnR() {

            }

            @Override
            public void turnT() {

            }

            @Override
            public void turnB() {

            }
        }, 'l');
    }

    public class GameUseIntegre{
        public void handOp(IGameIntegreHand gameHandOp,char op){
            switch (op){
                case  'l':
                    gameHandOp.turnLeft();
                    break;
            }
        }
        public void handBg(IGameIntegreBg backgroundOp,char op){
            switch (op){
                case  'v':
                    backgroundOp.openVoice();
                    break;
            }
        }
    }
    interface IGameIntegreHand{
        void turnLeft();
        void turnR();
        void turnT();
        void turnB();
    }
    interface IGameIntegreBg{
        void openVoice();
        void disableVoice();
        void beatyBack();
    }

}
