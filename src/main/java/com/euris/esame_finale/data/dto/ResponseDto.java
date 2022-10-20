package com.euris.esame_finale.data.dto;

import com.euris.esame_finale.utils.HttpRequestType;
import com.euris.esame_finale.utils.HttpResponseType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDto<Model> {

    private HttpRequestType httpRequest;
    private HttpResponseType httpResponse;
    private String code;
    private String desc;
    private Model body;
}
