set(NOT_EXISTS_V "no")

function(TestParentScope)
    set(FUN_NOT_EXISTS_V FALSE PARENT_SCOPE)
endfunction()