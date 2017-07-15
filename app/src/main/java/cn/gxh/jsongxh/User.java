package cn.gxh.jsongxh;

import java.util.List;

/**
 * Created by GXH on 2017/7/13.
 */
public class User {

    public String name;
    public long password;
    public boolean vip;
    private Info infos;

    public User(){}

    public User(String name, Long password, boolean isVip) {
        this.name = name;
        this.password = password;
        this.vip = isVip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Info getInfos() {
        return infos;
    }

    public void setInfos(Info infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "name:"+name+","+"password:"+password+","+"vip:"+vip+","+"info:"+infos.toString();
    }

    public static class Info{
        int age;
        String birthday;
        int height;
        private List<Hobby> hobbies;

        public Info(int age, String birthday, int height, List<Hobby> hobbies) {
            this.age = age;
            this.birthday = birthday;
            this.height = height;
            this.hobbies = hobbies;
        }

        public Info(){}

        @Override
        public String toString() {
            String str="";
            for(int i=0;i<hobbies.size();i++){
                str+=hobbies.get(i).toString();
            }
            return "age:"+age+","+"birthday:"+birthday+"hobby:"+str;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public List<Hobby> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<Hobby> hobbies) {
            this.hobbies = hobbies;
        }

        public static class Hobby{
            String type;
            String imgUrl;

            @Override
            public String toString() {
                return "type:"+type+","+"imgUrl:"+imgUrl+"--";
            }

            public Hobby(){}

            public Hobby(String type, String imgUrl) {
                this.type = type;
                this.imgUrl = imgUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
