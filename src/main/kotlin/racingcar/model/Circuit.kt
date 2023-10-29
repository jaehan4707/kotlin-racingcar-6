package racingcar.model

import racingcar.util.RandomGenerator
import racingcar.view.OutputView

class Circuit(private val outputView: OutputView = OutputView()) {
    private var _state : CircuitState = CircuitState.Start
    val state get() = _state
    private val _cars : MutableList<Car> = mutableListOf()
    val cars  : List<Car> get() = _cars

    fun makeCars(names: List<String>){ //TODO 입력받은 이름들로 자동차 추가하기
        for (name in names){
            _cars.add(Car(name))
        }
        _state = CircuitState.Racing
    }
    fun moveCars(attempt : Int){ //TODO 시도횟수만큼 랜덤숫자를 생성해서 자동차 움직이기
        repeat(attempt){
            for(car in cars){
                car.attemptMove(RandomGenerator.makeRandomNumber())
            }
            outputView.printGameStatusMessage(cars)
        }
        _state = CircuitState.End
    }

    fun findWinner() : List<Car>{
        val maxPosition = cars.maxOf{ it.move.position }
        _state = CircuitState.Exit
        return  cars.filter { it.move.position == maxPosition }
    }
}