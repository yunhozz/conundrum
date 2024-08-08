package advance.composition;

import java.util.List;

interface Do369Processor {
    String play(int number);
}

interface NumberProcessor {
    String run(int number);
}

abstract class NumberProcessorImpl implements NumberProcessor {
    protected static final List<Integer> arr = List.of(3, 6, 9);

    protected abstract String process(int number);

    @Override
    public String run(int number) {
        return process(number);
    }
}

class Do369InSeoul implements Do369Processor {
    private final NumberProcessor numberProcessor;

    public Do369InSeoul() {
        numberProcessor = new NumberProcessorImpl() {
            @Override
            public String process(int number) {
                String numStr = String.valueOf(number);
                for (char c : numStr.toCharArray()) {
                    if (arr.contains(Character.getNumericValue(c))) {
                        return "clap";
                    }
                }
                return numStr;
            }
        };
    }

    @Override
    public String play(int number) {
        return numberProcessor.run(number);
    }
}

class Do369InBusan implements Do369Processor {
    private final NumberProcessor numberProcessor;

    public Do369InBusan() {
        numberProcessor = new NumberProcessorImpl() {
            @Override
            public String process(int number) {
                String numStr = String.valueOf(number);
                StringBuilder str = new StringBuilder();
                boolean flag = false;
                for (char c : numStr.toCharArray()) {
                    if (arr.contains(Character.getNumericValue(c))) {
                        flag = true;
                        str.append("clap");
                    }
                }
                return flag ? str.toString() : numStr;
            }
        };
    }

    @Override
    public String play(int number) {
        return numberProcessor.run(number);
    }
}

class GameOf369 {

    public static void main(String[] args) throws Exception {
        String region = "부산";
        String[] playerNames = {"player1", "player2", "player3"};
        int maxGameCount = 100;

        GameOf369 gameOf369 = new GameOf369();
        String result = gameOf369.play(region, playerNames, maxGameCount);
        System.out.println(result);
    }

    public String play(String region, String[] playerNames, int maxGameCount) {
        Do369Processor processor = createDo369ByRegion(region);
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= maxGameCount; i++) {
            int playerNum = playerNames.length;
            int index = i % playerNum != 0 ? (i % playerNum) - 1 : playerNum - 1;
            answer.append(playerNames[index])
                    .append(": ")
                    .append(processor.play(i))
                    .append("\n");
        }
        return answer.toString();
    }

    private Do369Processor createDo369ByRegion(String region) {
        switch (region) {
            case "서울":
                return new Do369InSeoul();
            case "부산":
                return new Do369InBusan();
            default:
                throw new IllegalArgumentException("Unsupported region: " + region);
        }
    }
}