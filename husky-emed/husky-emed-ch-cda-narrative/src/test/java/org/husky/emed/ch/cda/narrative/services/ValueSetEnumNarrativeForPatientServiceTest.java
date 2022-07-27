package org.husky.emed.ch.cda.narrative.services;

import org.apache.commons.lang3.StringUtils;
import org.husky.common.enums.ValueSetEnumInterface;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.enums.RouteOfAdministrationAmbu;
import org.husky.emed.ch.enums.TimingEventAmbu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests of the {@link ValueSetEnumNarrativeForPatientService} class.
 *
 * @author Ronaldo Loureiro
 */
class ValueSetEnumNarrativeForPatientServiceTest {

    ValueSetEnumNarrativeForPatientService valueSetEnumNarrativeForPatientService;

    ValueSetEnumNarrativeForPatientServiceTest() throws IOException {
        this.valueSetEnumNarrativeForPatientService = new ValueSetEnumNarrativeForPatientService();
    }

    @Test
    void testValueSetEnumNarrativeIfNonEmptyValues() {
        ValueSetEnumInterface[] valueSetEnum = Stream.of(
                RouteOfAdministrationAmbu.values(),
                TimingEventAmbu.values())
                .flatMap(Stream::of)
                .toArray(ValueSetEnumInterface[]::new);

        for (ValueSetEnumInterface enumm : valueSetEnum) {
            for (NarrativeLanguage narrativeLanguage: NarrativeLanguage.values()) {
                assertNotEquals(this.valueSetEnumNarrativeForPatientService.getMessage(enumm, narrativeLanguage),
                        StringUtils.EMPTY);
            }
        }
    }
}
