package fabiomarras.u5w2d2.payloads;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewBlogRequestDTO(
        String categoria,
        @NotNull(message = "il titolo è obbligatorio")
        @Size(min = 3, message = "Il titolo deve avere minimo 3 caratteri e massimo 15")
        String titolo,
        @NotNull(message = "la cover è obbligatoria")
        String cover,
        @NotNull
        String contenuto,
        int tempoDiLettura,
        String avatar,
        List<String> errorsList
) {
}
