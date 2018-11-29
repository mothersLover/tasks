def compress_string(string):
    char_dict = dict()
    char_list = []
    res = ''
    for char in string:
        if char not in char_dict:
            char_dict[char] = 1
            char_list.append(char)
        else:
            char_dict[char] += 1
    for char in char_list:
        char_ = char_dict[char]
        res += char
        res += str(char_)
    if len(res) < len(string):
        return res
    else:
        return string


res = compress_string("aabccccaaaa")
print(res)

