package nextstep.subway.maps.fare.domain;

import nextstep.subway.maps.line.domain.Line;
import nextstep.subway.maps.map.domain.LineStationEdge;
import nextstep.subway.maps.map.domain.SubwayPath;

public class FareContext {
    public static final int DEFAULT_FARE = 1250;
    private final Fare fare;
    private final SubwayPath subwayPath;


    public FareContext(SubwayPath subwayPath) {
        this.fare = new Fare(DEFAULT_FARE);
        this.subwayPath = subwayPath;
    }

    public int getDistance() {
        return this.subwayPath.calculateDistance();
    }

    public int getExtraFare() {
        Line expensiveLine = this.subwayPath.getLineStationEdges().stream()
                .map(LineStationEdge::getLine)
                .max((line, line2) -> {
                    int extraFare = line.getExtraFare();
                    int extraFare2 = line2.getExtraFare();

                    return extraFare - extraFare2;
                }).orElseThrow(RuntimeException::new);

        return expensiveLine.getExtraFare();
    }

    public Fare getFare() {
        return this.fare;
    }
}
