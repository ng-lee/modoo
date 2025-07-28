package com.modoo.domain.clubs.dto.request;

import com.modoo.domain.clubs.entity.Clubs;
import com.modoo.domain.clubs.entity.ClubsFile;
import com.modoo.global.entity.ImageFile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubsFileRequest {

    private Long clubsCd;
    private Long fileCd;

}
