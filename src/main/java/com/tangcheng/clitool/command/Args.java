package com.tangcheng.clitool.command;

import com.beust.jcommander.Parameter;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.util.ArrayList;
import java.util.List;

@Data
public class Args {
    @Parameter(names = {"-m", "--mobile"}, variableArity = true, description = "电话号码", required = true, echoInput = true, help = true)
    private List<String> mobiles = new ArrayList<>();
}