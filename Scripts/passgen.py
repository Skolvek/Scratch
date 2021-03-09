#!python3

import sys
import string
import secrets

USAGE_NOTE = "NOTE:\n  To exclude \' , \" , [ , ] characters they must be\n  entered as \\\" or \\[ (these are special chars that need to be escaped)"

def show_usage():
    print("\nPassword Generator")
    print("-l|--length <int>       Length of password to generate (default length 10)")
    print("-a|--allow <string>     Allowed special characters. Entered as string: $%!@")
    print("-e|--exclude <string>   Excluded special characters. Entered as string: $%!@")
    print("-c|--excludecommon      Excludes characters thar are commonly rejected by password validators (overrides -e)")
    print("-h|--help               Show this message")

    print(USAGE_NOTE)
    return

# specialChars: string -> special characters in password
# allow: bool -> add/remove from charList
def build_charset(specialChars, allow):
    #start with all numbers and letters
    charSet = list(string.ascii_letters + string.digits)

    if not allow:
        #add special chars, then start removal process
        charSet.extend(list(string.punctuation))
        charSet = list(filter(lambda x: x not in specialChars, charSet))
    else:
        charSet.extend(list(specialChars))
    
    return "".join(charSet)

# length: int -> length of password to generate
# charSet: string -> set of characters allowed to be used in generation
def gen_pass(length, charSet):
    return ''.join(secrets.choice(charSet) for i in range(length))

def main():
    #by default, use all ASCII Punctuation for specail chars and produce password of length 10
    length = 10
    specialChars = string.punctuation
    allow = True
    args = sys.argv[1:]
    
    if len(sys.argv) <= 1:
        show_usage()
        return
    
    for idx, val in enumerate(args):
        if val == "-l" or val == "--length":
            length = int(args[idx+1])
        if val == "-a" or val == "--allow":
            specialChars = args[idx+1]
        if val == "-e" or val == "--exclude":
            allow = False
            #chars like " and ' need to be replaced with \" and \'
            specialChars = args[idx+1]
        if val == "-c" or val == "--excludecommon":
            allow = True #override -e
            #different websites have different practices... though
            #you should not generally restrict the character pool
            #... websites should really just sanitize all user input...
            specialChars = "-_$*()#@%/.| "
        if val == "-h" or val == "--help":
            show_usage()
            return 0
    
    charSet = build_charset(specialChars, allow)
    
    if not allow:
        print(USAGE_NOTE)
    print("\nGeneration Settings:")
    print("  Length:", str(length))
    print("  Chars Allowed:", charSet)

    pwd = gen_pass(length, charSet)

    print("\nGenerated:",pwd)

    return 0

if __name__ == "__main__":
    main()