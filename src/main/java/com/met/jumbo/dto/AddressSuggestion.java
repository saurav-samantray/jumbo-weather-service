package com.met.jumbo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@AllArgsConstructor
@ToString
public class AddressSuggestion {
    private String label;
    private SuggestionType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressSuggestion that = (AddressSuggestion) o;
        return label.equals(that.label) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, type);
    }
}
