package fulopbence.nye.progtech.service.map.validation.impl;

import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.exception.MapValidationException;
import fulopbence.nye.progtech.service.map.validation.MapValidator;

import java.util.List;

/**
 * Composes a validation list from different validators.
 */

public class MapValidatorComposer implements MapValidator {

    private final List<MapValidator> mapValidatorList;

    public MapValidatorComposer(List<MapValidator> mapValidatorList) {
        this.mapValidatorList = mapValidatorList;
    }

    @Override
    public void validate(MapVo mapVo) throws MapValidationException {
        for (MapValidator mapValidator : mapValidatorList) {
            mapValidator.validate(mapVo);
        }

    }
}
