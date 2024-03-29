package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("처음 점수는 음수로 초기화 할 수 없다.")
    void Given_Score_When_createdByValue_Then_Exception() {
        //given, when, then
        assertThatThrownBy(() -> new Score(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수로 점수를 초기화 할 수 없습니다.");
    }

    @Test
    @DisplayName("두 점수가 주어지면 더할 수 있다.")
    void Given_Score_Then_AddAnotherScore_Then_AddedResult() {
        //given
        Score score = new Score(10);
        //when, then
        assertThat(score.add(new Score(20))).isEqualTo(new Score(30));
    }

    @Test
    @DisplayName("두 점수가 주어지면 뺄 수 있다.")
    void Given_Score_When_SubtractAnotherScore_Then_SubstrateResult() {
        //given
        Score score = new Score(10);
        //when, then
        assertThat(score.subtract(new Score(5))).isEqualTo(new Score(5));
    }

    @Test
    @DisplayName("현재 가진 금액의 비율을 곱해서 새로운 금액을 만들 수 있다.")
    void Given_Score_When_MultiplyWithRatio_Then_MultiplyResult() {
        //given
        Score score = new Score(10);
        //when, then
        assertThat(score.multiply(0.5)).isEqualTo(new Score(5));
    }
}
